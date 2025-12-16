package integration.controller;

import integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.example.dto.UserCreateEditDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc   // чтобы имитировать http-запросы в тестах
@RequiredArgsConstructor
class UserControllerTest extends IntegrationTestBase {

    private final MockMvc mockMvc; // имитрирует http-запросы

    @Test
    void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .param(UserCreateEditDto.Fields.name, "test@gmail.com")
                        .param(UserCreateEditDto.Fields.firstname, "testfirstname")
                        .param(UserCreateEditDto.Fields.lastname, "lastname")
                        .param(UserCreateEditDto.Fields.birthDate, "2001-01-01"))
                .andExpectAll(
                        status().is3xxRedirection()
                );
    }

}