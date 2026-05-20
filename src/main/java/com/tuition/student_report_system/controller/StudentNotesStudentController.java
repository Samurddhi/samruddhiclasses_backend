package com.tuition.student_report_system.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuition.student_report_system.exception.ResourceNotFoundException;
import com.tuition.student_report_system.model.Student;
import com.tuition.student_report_system.model.StudentNote;
import com.tuition.student_report_system.repository.StudentNoteRepository;
import com.tuition.student_report_system.repository.StudentRepository;

import java.security.Principal;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*")
public class StudentNotesStudentController {

    private final StudentRepository studentRepository;
    private final StudentNoteRepository studentNoteRepository;

    public StudentNotesStudentController(StudentRepository studentRepository, StudentNoteRepository studentNoteRepository) {
        this.studentRepository = studentRepository;
        this.studentNoteRepository = studentNoteRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<List<StudentNote>> getMyNotes(Principal principal) {
        String email = principal.getName();
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for email: " + email));

        String studentId = student.getId();
        return ResponseEntity.ok(studentNoteRepository.findByStudentId(studentId));
    }
}

