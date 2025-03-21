package cz.upce.fei.nnpiacv.controller;

import cz.upce.fei.nnpiacv.component.JwtUtil;
import cz.upce.fei.nnpiacv.model.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String token = jwtUtil.generateToken(loginRequest.username());
        return ResponseEntity.ok(token);
    }
}