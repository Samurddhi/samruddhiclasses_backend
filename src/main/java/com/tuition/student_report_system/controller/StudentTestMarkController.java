package com.tuition.student_report_system.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuition.student_report_system.model.Student;
import com.tuition.student_report_system.model.TestMark;
import com.tuition.student_report_system.repository.StudentRepository;
import com.tuition.student_report_system.repository.TestMarkRepository;

import java.security.Principal;

import com.tuition.student_report_system.exception.ResourceNotFoundException;

import java.util.Collections;



@RestController
@RequestMapping("/api/test-marks")
@CrossOrigin(origins = "*")
public class StudentTestMarkController {


    private final StudentRepository studentRepository;
    private final TestMarkRepository testMarkRepository;

    public StudentTestMarkController(StudentRepository studentRepository, TestMarkRepository testMarkRepository) {
        this.studentRepository = studentRepository;
        this.testMarkRepository = testMarkRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<List<TestMark>> getMyMarks(Principal principal) {
        String email = principal.getName();
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for email: " + email));

        String reg = student.getRegistrationNumber();
        if (reg == null || reg.isBlank()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<TestMark> byRoll = testMarkRepository.findByRollNumberContainingIgnoreCase(reg);

        // exact (case-insensitive) filter
        List<TestMark> filtered = byRoll.stream()
                .filter(tm -> tm.getRollNumber() != null && tm.getRollNumber().equalsIgnoreCase(reg))
                .collect(java.util.stream.Collectors.toList());

        return ResponseEntity.ok(filtered);
    }
}


