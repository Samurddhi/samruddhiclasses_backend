package com.tuition.student_report_system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuition.student_report_system.dto.LoginRequest;
import com.tuition.student_report_system.dto.LoginResponse;
import com.tuition.student_report_system.dto.RegisterRequest;
import com.tuition.student_report_system.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
    try {
        LoginResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new com.tuition.student_report_system.dto.ErrorResponse(
                e.getMessage(), "BAD_REQUEST"));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new com.tuition.student_report_system.dto.ErrorResponse(
                "Registration failed: " + e.getMessage(), "INTERNAL_SERVER_ERROR"));
    }
}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new com.tuition.student_report_system.dto.ErrorResponse(
                    e.getMessage(), "UNAUTHORIZED"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new com.tuition.student_report_system.dto.ErrorResponse(
                    "Login failed: " + e.getMessage(), "INTERNAL_SERVER_ERROR"));
        }
    }
}