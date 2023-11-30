package by.nikita.spring.database.repository;

import by.nikita.spring.database.entity.User;
import by.nikita.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByFilter(UserFilter filter);
}
