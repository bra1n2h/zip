package by.nikita.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {

//    @Bean("connectionPool1")
//    @Scope(BeanDefinition.SCOPE_SINGLETON)
//    public ConnectionPool connectionPool(@Value("${db.username}") String username){
//        return new ConnectionPool(username, "root", 20, "url");
//    }
//
//    @Bean
//    public ConnectionPool connectionPool2(){
//        return new ConnectionPool("sad", "pass", 100, "---");
//    }

//    @Bean
//    @Profile("web&prod")
//    public UserRepository userRepository(ConnectionPool connectionPool){
//        return new UserRepository(connectionPool);
//    }
}
