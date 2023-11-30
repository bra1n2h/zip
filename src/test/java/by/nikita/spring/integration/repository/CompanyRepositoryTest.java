package by.nikita.spring.integration.repository;

import by.nikita.spring.annotations.IT;
import by.nikita.spring.database.entity.Company;
import by.nikita.spring.database.repository.CompanyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
 //@Commit
public class CompanyRepositoryTest {

    private static final Integer APPLE_ID = 4;
    @PersistenceContext
    private final EntityManager entityManager;
    private final CompanyRepository companyRepository;

    @Test
    void delete(){
        var maybeCompany = companyRepository.findById(APPLE_ID);
        assertTrue(maybeCompany.isPresent());
        maybeCompany.ifPresent(companyRepository::delete);
        entityManager.flush();
        assertTrue(companyRepository.findById(APPLE_ID).isEmpty());
    }

    @Test
    void findById(){
        var company = entityManager.find(Company.class, 1);
        assertNotNull(company);
        assertThat(company.getLocales()).hasSize(2);
    }

    @Test
    void checkFindByQueries(){
        companyRepository.findByName("Google");
//        var companies = companyRepository.findAllByNameContainingIgnoreCase("a");
//        assertThat(companies).hasSize(3);
    }

//    @Test
//    void save(){
//        var company = Company.builder()
//                .name("Apple2")
//                .locales(Map.of(
//                        "ru", "Apple описание",
//                        "en", "Apple description"
//                ))
//                .build();
//        entityManager.persist(company);
//        assertNotNull(company.getId());
//    }
}
