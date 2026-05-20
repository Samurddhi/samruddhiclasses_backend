package com.tuition.student_report_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tuition.student_report_system.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults())   // ✅ enable CORS support
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                    // allow preflight requests
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    // public auth endpoints
                    .requestMatchers("/api/auth/**").permitAll()
                    // admin-only APIs
                    .requestMatchers("/api/admin/**").hasRole("ADMIN")
                    // student-safe read endpoint
                    .requestMatchers("/api/test-marks/me", "/api/test-marks/me/").permitAll()
                    // admin-only APIs
                    .requestMatchers("/api/test-marks/**").hasRole("ADMIN")
                    .requestMatchers("/api/notes/me").authenticated()
                    // all other APIs require a valid token
                    .requestMatchers("/api/students/**", "/api/timetables/**").authenticated()
                    .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}