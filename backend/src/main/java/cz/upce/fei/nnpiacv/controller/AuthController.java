package cz.upce.fei.nnpiacv.controller;

import cz.upce.fei.nnpiacv.component.JwtUtil;
import cz.upce.fei.nnpiacv.domain.LoginRequest;
import cz.upce.fei.nnpiacv.domain.User;
import cz.upce.fei.nnpiacv.exception.UserNotFoundException;
import cz.upce.fei.nnpiacv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private final JwtUtil _jwtUtil;

    @Autowired
    private final UserService _userService;

    public AuthController(JwtUtil jwtUtil, UserService userService) {
        this._jwtUtil = jwtUtil;
        this._userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

        try {
            User user = _userService.getUserByEmail(loginRequest.email());

            if (!user.getPassword().equals(loginRequest.password())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
            }

            String token = _jwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(token);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}