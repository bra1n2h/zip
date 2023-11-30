package by.nikita.spring.database.repository;

import by.nikita.spring.database.entity.Role;
import by.nikita.spring.database.entity.User;
import by.nikita.spring.dto.IPersonalInfo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long >, FilterUserRepository,
        QuerydslPredicateExecutor<User>
{


//    Slice or Page(extends Slice has more methods)
//    Page<User> findAllBy(Pageable pageable);

    List<User> findFirst3By(Sort sort);

    Optional<User> findFirstByCompanyIsNotNullOrderByIdDesc();

    @Query(value = "select u.firstname, u.lastname, u.birth_date from users u" +
            " where company_id = :companyId ",
    nativeQuery = true)
    List<IPersonalInfo> findAllByCompanyId(Integer companyId);

    @Query("select u from User u " +
            "where u.firstname like %:firstname% and u.lastname like %:lastname%")
    List<User> findAllByFirstnameContainingAndLastnameContaining(String firstname, String lastname);

    @Query(value = "SELECT u.* FROM users u WHERE u.username = :username",
    nativeQuery = true)
    List<User> findAllByUsername(String username);

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.role = :role " +
            "where u.id in (:ids)")
    int updateRole(Role role, Long... ids);

    Optional<User> findByUsername(String username);


//    @Autowired
//    @Qualifier("connectionPool1")
//    private ConnectionPool connectionPool1;
//
//    @Value("${db.pool.size}")
//    private Integer poolSize;
//
//    @Autowired
//    private List<ConnectionPool> connectionPool;
//
//    public UserRepository(ConnectionPool connectionPool1) {
//        this.connectionPool1 = connectionPool1;
//    }

    //    @PostConstruct
//    public void init(){
//        System.out.println("Init UserRepository");
//    }
//
//    @PreDestroy
//    public void destroy(){
//        System.out.println("Destroy UserRepository");
//    }
}
