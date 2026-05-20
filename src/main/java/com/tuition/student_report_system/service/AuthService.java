package com.tuition.student_report_system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tuition.student_report_system.dto.LoginRequest;
import com.tuition.student_report_system.dto.LoginResponse;
import com.tuition.student_report_system.dto.RegisterRequest;
import com.tuition.student_report_system.model.User;
import com.tuition.student_report_system.model.Student;
import com.tuition.student_report_system.repository.UserRepository;
import com.tuition.student_report_system.repository.StudentRepository;
import com.tuition.student_report_system.security.JwtUtil;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;

    public AuthService(UserRepository userRepository,
                   JwtUtil jwtUtil,
                   PasswordEncoder passwordEncoder,
                   StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.studentRepository = studentRepository;
    }

    public LoginResponse register(RegisterRequest request) {
        // check if user exists
        userRepository.findByEmail(request.getEmail())
            .ifPresent(u -> {
                throw new RuntimeException("User already exists");
            });
        
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? request.getRole() : "STUDENT");
        user = userRepository.save(user);

        // NEW: if this is a student, create Student doc
        if ("STUDENT".equalsIgnoreCase(user.getRole())) {
            Student student = new Student(request.getName(), request.getEmail(), request.getRegistrationNumber(), request.getSemester());
            studentRepository.save(student);
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        return new LoginResponse(token, user.getRole());
    }

    public LoginResponse login(LoginRequest request) {
        logger.info("Login attempt for email: {}", request.getEmail());
        
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    logger.warn("Login failed: User not found with email: {}", request.getEmail());
                    return new RuntimeException("User not found");
                });

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            logger.warn("Login failed: Invalid password for email: {}", request.getEmail());
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        logger.info("Login successful for email: {}", request.getEmail());
        
        return new LoginResponse(token, user.getRole());
    }
}
