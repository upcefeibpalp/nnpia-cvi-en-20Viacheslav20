package cz.upce.fei.nnpiacv.controller;

import cz.upce.fei.nnpiacv.service.UserService;
import domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/user")
    public List<User> findUsers(@RequestParam(required = false) String email) {
        if (email != null) {
            Optional<User> user = userService.findUserByEmail(email);
            return user.map(List::of).orElse(List.of());
        } else {
            return userService.findUsers();
        }
    }
}
