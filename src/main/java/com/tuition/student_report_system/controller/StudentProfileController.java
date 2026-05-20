package com.tuition.student_report_system.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.tuition.student_report_system.exception.ResourceNotFoundException;

import com.tuition.student_report_system.model.Student;
import com.tuition.student_report_system.repository.StudentRepository;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentProfileController {

    private final StudentRepository studentRepository;

    public StudentProfileController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PatchMapping("/me/details")
    public ResponseEntity<Student> updateMyDetails(Principal principal, @RequestBody Map<String, String> details) {

        if (principal == null || principal.getName() == null || principal.getName().isBlank()) {
            throw new IllegalArgumentException("Unauthorized: principal is missing");
        }
        if (details == null || details.isEmpty()) {
            throw new IllegalArgumentException("Request body is missing or empty");
        }

        String email = principal.getName().trim();


        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for email: " + email));

        if (details.containsKey("name")) {
            student.setName(details.getOrDefault("name", student.getName()));
        }
        if (details.containsKey("registrationNumber")) {
            student.setRegistrationNumber(details.getOrDefault("registrationNumber", student.getRegistrationNumber()));
        }
        if (details.containsKey("semester")) {
            student.setSemester(details.getOrDefault("semester", student.getSemester()));
        }

        // keep existing statuses logic out; this endpoint is only for profile fields.
        // statuses are managed elsewhere.

        studentRepository.save(student);
        return ResponseEntity.ok(student);
    }
}

