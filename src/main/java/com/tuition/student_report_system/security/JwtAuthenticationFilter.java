package com.tuition.student_report_system.security;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tuition.student_report_system.model.User;
import com.tuition.student_report_system.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            String email = jwtUtil.extractEmail(token);
            String roleFromToken = jwtUtil.extractRole(token);

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null
                    && jwtUtil.validateToken(token, email)) {

                User user = userRepository.findByEmail(email).orElse(null);
                if (user == null) {
                    filterChain.doFilter(request, response);
                    return;
                }

                // Prefer DB role to avoid stale/incorrect role claims in old tokens.
                String normalizedRole = normalizeRole(
                        user.getRole() != null ? user.getRole() : roleFromToken
                );
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        List.of(new SimpleGrantedAuthority(normalizedRole)));

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }

    private String normalizeRole(String role) {
        if (role == null || role.isBlank()) {
            return "ROLE_USER";
        }
        String value = role.trim().toUpperCase(Locale.ROOT);
        return value.startsWith("ROLE_") ? value : "ROLE_" + value;
    }
}
