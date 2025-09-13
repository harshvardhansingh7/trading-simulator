package com.trading.simulator.controller;


import com.trading.simulator.config.JwtUtil;
import com.trading.simulator.dto.AuthRequest;
import com.trading.simulator.dto.AuthResponse;
import com.trading.simulator.dto.SignupRequest;
import com.trading.simulator.entity.User;
import com.trading.simulator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest req) {
        User u = userService.signup(req.getUsername(), req.getEmail(), req.getPassword());
        return "User created: " + u.getUsername();
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );
        String token = jwtUtil.generateToken(req.getUsername());
        return new AuthResponse(token);
    }
}
