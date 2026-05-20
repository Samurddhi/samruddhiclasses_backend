package com.tuition.student_report_system;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tuition.student_report_system.model.User;
import com.tuition.student_report_system.repository.UserRepository;

@SpringBootApplication
public class StudentReportSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentReportSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            // Check using EMAIL (not username)
            if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {

                User user = new User();
                user.setEmail("admin@gmail.com");   // ✅ use email
                user.setPassword(passwordEncoder.encode("admin123"));
                user.setRole("ADMIN");

                userRepository.save(user);

                System.out.println("Admin user created");
            }
        };
    }
}
