package cz.upce.fei.nnpiacv.controller;

import cz.upce.fei.nnpiacv.domain.User;
import cz.upce.fei.nnpiacv.exception.UserAlreadyExistsException;
import cz.upce.fei.nnpiacv.exception.UserNotFoundException;
import cz.upce.fei.nnpiacv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private final UserService _userService;

    public UserController(UserService userService) {
        this._userService = userService;
    }

    @PostMapping("/newUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = _userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).
                contentType(MediaType.APPLICATION_JSON)
                .body(savedUser);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = _userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/user/{id}/activate")
    public ResponseEntity<User> activateUser(@PathVariable Long id) {
        User user = _userService.setUserActiveStatus(id, true);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user/{id}/deactivate")
    public ResponseEntity<User> deactivateUser(@PathVariable Long id) {
        User user = _userService.setUserActiveStatus(id, false);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(_userService.getUserById(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findUsers(@RequestParam(required = false) String email) {
        if (email != null) {
            return ResponseEntity.ok(List.of(_userService.getUserByEmail(email)));
        } else {
            return ResponseEntity.ok(_userService.getUsers());
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        _userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

}
