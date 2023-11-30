package by.nikita.spring.annotations;

import by.nikita.spring.ApplicationRunner;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SpringBootTest(classes = ApplicationRunner.class)
@ExtendWith(SpringExtension.class)
@Target(ElementType.TYPE)
@ComponentScan("src/test/resources")
@ComponentScan(basePackages = "by.nikita.spring")
@Retention(RetentionPolicy.RUNTIME)
@Transactional
public @interface IT {
}
