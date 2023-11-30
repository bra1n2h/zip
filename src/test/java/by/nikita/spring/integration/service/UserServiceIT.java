package by.nikita.spring.integration.service;

import by.nikita.spring.annotations.IT;
import by.nikita.spring.database.entity.Role;
import by.nikita.spring.dto.UserCreateEditDto;
import by.nikita.spring.dto.UserReadDto;
import by.nikita.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
public class UserServiceIT {

    private static final Long USER_1 = 1L;
    private static final Integer COMPANY_1 = 1;
    private final UserService userService;

    @Test
    void findAll(){
        List<UserReadDto> result = userService.findAll();
        assertThat(result).hasSize(5);
    }

    @Test
    void findById(){
        Optional<UserReadDto> maybeUser = userService.findById(USER_1);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> assertEquals("ivan@gmail.com", user.getUsername()));
    }

    @Test
    void create(){
        UserCreateEditDto  userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "Test",
                "Test",
                "test",
                Role.ADMIN,
                COMPANY_1,
                new MockMultipartFile("test", new byte[0])
        );
        UserReadDto actualResult = userService.create(userDto);

        assertEquals(userDto.getUsername(), actualResult.getUsername());
        assertEquals(userDto.getBirthDate(), actualResult.getBirthDate());
        assertSame(userDto.getRole(), actualResult.getRole());
        assertEquals(userDto.getLastname(), actualResult.getLastname());
        assertEquals(userDto.getFirstname(), actualResult.getFirstname());
        assertEquals(userDto.getCompanyId(), actualResult.getCompany().id());
    }
    @Test
    void update(){
        UserCreateEditDto  userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "Test",
                "Test",
                "test",
                Role.ADMIN,
                COMPANY_1,
                new MockMultipartFile("test", new byte[0])
        );
        Optional<UserReadDto> actualResult = userService.update(USER_1, userDto);

        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(user -> {
            assertEquals(userDto.getUsername(), user.getUsername());
            assertEquals(userDto.getBirthDate(), user.getBirthDate());
            assertSame(userDto.getRole(), user.getRole());
            assertEquals(userDto.getLastname(), user.getLastname());
            assertEquals(userDto.getFirstname(), user.getFirstname());
            assertEquals(userDto.getCompanyId(), user.getCompany().id());
        });
    }

    @Test
    void delete(){
        assertFalse(userService.delete(-124L));
        assertTrue(userService.delete(1L));
    }
}
