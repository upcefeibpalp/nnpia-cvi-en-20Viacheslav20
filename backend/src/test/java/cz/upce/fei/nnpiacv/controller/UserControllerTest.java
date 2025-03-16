package cz.upce.fei.nnpiacv.controller;

import cz.upce.fei.nnpiacv.service.UserService;
import cz.upce.fei.nnpiacv.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    // Definování testovacího uživatele
    private static final User testUser = new User(1L, "some_password", "test@example.com");

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    // Tato metoda se provede před každým testem
    @BeforeEach
    void setUp() {
        // Nastavení Mocku. Ve chvíli kdy někdo zavolá metodu findUserById na mockované UserService, vrátím Optional s
        // s testovacím uživatelem
        Mockito.when(userService.findUserById(1L)).thenReturn(Optional.of(testUser));
    }

    @Test
    void getUser_shouldReturnUser_whenUserExists() throws Exception {
        // Zavolám endpoint po získání konkrétního uživatele
        mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
                // Ověřím jestli se vrátil 2xx status indikující úspěch
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Ověřím jestli vrácený JSON obsahuje atribut id s hodnotou 1
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                // Ověřím jestli vrácený JSON obsahuje atribut email s hodnotou test@example.com
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("test@example.com"));
    }

}
