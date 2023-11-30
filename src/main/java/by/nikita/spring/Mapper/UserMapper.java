package by.nikita.spring.Mapper;

import by.nikita.spring.dto.UserDto;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@ToString
@Component
public class UserMapper {

    @Autowired
    private UserDto userDto;
}
