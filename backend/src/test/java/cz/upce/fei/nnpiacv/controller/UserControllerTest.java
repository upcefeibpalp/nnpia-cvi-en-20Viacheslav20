package cz.upce.fei.nnpiacv.controller;

import cz.upce.fei.nnpiacv.service.UserService;
import domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
@TestPropertySource(
        locations = "classpath:application.properties")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;


    @Test
    void getUser_shouldReturnUser_whenUserExists() throws Exception {
        User user = new User(1L, "some_password", "test@example.com");
        when(userService.findUserById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.password").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("test@example.com"));
    }

}
