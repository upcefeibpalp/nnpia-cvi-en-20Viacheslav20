package cz.upce.fei.nnpiacv.controller;

import cz.upce.fei.nnpiacv.domain.User;
import cz.upce.fei.nnpiacv.exception.UserAlreadyExistsException;
import cz.upce.fei.nnpiacv.exception.UserNotFoundException;
import cz.upce.fei.nnpiacv.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    public void getUser_shouldReturnUser_whenUserExists() throws Exception {
        User user = new User("some_password", "test@example.com");

        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/user/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    public void getUser_shouldReturn404_whenUserDoesNotExist() throws Exception {
        when(userService.getUserById(anyLong())).thenThrow(new UserNotFoundException(999L));

        mockMvc.perform(get("/user/999"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(content().string("User with ID 999 not found."));
    }

    @Test
    public void testAddUserSuccess() throws Exception {

        User mockUser = new User("pass123", "testUser@example.com");

        when(userService.addUser(any(User.class))).thenReturn(mockUser);

        String jsonPayload = """
        {
            "email": "testUser@example.com",
            "password": "pass123"
        }
        """;

        mockMvc.perform(post("/newUser")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonPayload))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.email").value("testUser@example.com"));
    }

    @Test
    public void shouldReturn409WhenUserAlreadyExists() throws Exception {
        User mockUser = new User("pass123", "testUser@example.com");

        when(userService.addUser(any(User.class))).
                thenThrow(new UserAlreadyExistsException(mockUser.getEmail()));

        String testUser = """
        {
            "email": "testUser@example.com",
            "password": "pass123"
        }
        """;

        mockMvc.perform(post("/newUser")
            .contentType(MediaType.APPLICATION_JSON)
            .content(testUser))
            .andExpect(status().isConflict())
            .andExpect(content().string("User with email testUser@example.com already exists."));
    }
}
