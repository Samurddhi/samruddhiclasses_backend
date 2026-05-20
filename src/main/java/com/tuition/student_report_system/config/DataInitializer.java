package com.tuition.student_report_system.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tuition.student_report_system.model.User;
import com.tuition.student_report_system.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByEmail("admin@gmail.com").isEmpty()) {
                User admin = new User();
                admin.setEmail("admin@gmail.com");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole("ADMIN");
                repo.save(admin);
            }
        };
    }
}
