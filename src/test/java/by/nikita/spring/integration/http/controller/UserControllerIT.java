package by.nikita.spring.integration.http.controller;

import by.nikita.spring.annotations.IT;
import by.nikita.spring.dto.UserCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IT
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class UserControllerIT {
    private final MockMvc mockMvc;

    @Test
    @WithMockUser(username = "test@gmail.com", password = "test", authorities = {"ADMIN", "USER"})
    void findAll() throws Exception{
        mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    void create() throws Exception{
        mockMvc.perform(post("/users")
                .param(UserCreateEditDto.Fields.username, "test@gmail.com")
                .param(UserCreateEditDto.Fields.birthDate, "20-12-2003")
                .param(UserCreateEditDto.Fields.firstname, "Test")
                .param(UserCreateEditDto.Fields.lastname, "Test")
                .param(UserCreateEditDto.Fields.role, "ADMIN")
                .param(UserCreateEditDto.Fields.companyId, "1")
        )
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrlPattern("/users/{\\d+}")
                );
    }
}
