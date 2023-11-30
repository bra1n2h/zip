package by.nikita.spring.integration.repository;

import by.nikita.spring.annotations.IT;
import by.nikita.spring.database.entity.Role;
import by.nikita.spring.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@IT
@RequiredArgsConstructor
public class UserRepositoryTest {

    private final UserRepository userRepository;

//    @Test
//    void checkPageable(){
//        var pageable = PageRequest.of(1, 2, Sort.by("id"));
//        var page = userRepository.findAllBy(pageable);
//        page.forEach(user -> System.out.println(user.getId()));
//        while (page.hasNext()){
//            page = userRepository.findAllBy(page.nextPageable());
//            page.forEach(user -> System.out.println(user.getId()));
//            System.out.println(page.getTotalPages());
//        }
//    }

    @Test
    void findFirst3By(){
        var users = userRepository.findFirst3By(Sort.by("id").descending());
        assertFalse(users.isEmpty());
        assertThat(users).hasSize(3);
    }

    @Test
    void findFirstByCompanyIsNotNullOrderByIdDescTest(){
        var user = userRepository.findFirstByCompanyIsNotNullOrderByIdDesc();
        assertThat(user.isPresent());
        user.ifPresent(u -> assertEquals("Kate", u.getFirstname()));
    }

    @Test
    void checkProjections(){
        var users = userRepository.findAllByCompanyId(1);
        assertThat(users).hasSize(3);
    }

    @Test
    void findUserQuery(){
        var users = userRepository.findAllByFirstnameContainingAndLastnameContaining("a", "ov");
        assertFalse(users.isEmpty());
        assertThat(users).hasSize(3);
    }



    @Test
    void updateRole(){
        var entity1 = userRepository.findById(1L);
        assertEquals(Role.ADMIN, entity1.get().getRole());
        var result = userRepository.updateRole(Role.USER, 1L, 5L);
        assertEquals(2, result);
        var entity2 = userRepository.findById(1L);
        assertEquals(Role.USER, entity2.get().getRole());
    }
}
