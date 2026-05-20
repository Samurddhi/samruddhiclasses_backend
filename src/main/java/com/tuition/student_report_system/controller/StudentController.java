package com.tuition.student_report_system.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import com.tuition.student_report_system.exception.ResourceNotFoundException;

import com.tuition.student_report_system.model.Student;
import com.tuition.student_report_system.repository.StudentRepository;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {
    
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<Student> getCurrentStudent(Principal principal) {
        String email = principal.getName();
        return studentRepository.findByEmail(email)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable String id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found: " + id));
    }

    @PatchMapping("/{id}/details")
    public ResponseEntity<Student> updateDetails(@PathVariable String id, @RequestBody Map<String, String> details) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found: " + id));


        if (details.containsKey("name")) {
            student.setName(details.getOrDefault("name", student.getName()));
        }
        if (details.containsKey("registrationNumber")) {
            student.setRegistrationNumber(details.getOrDefault("registrationNumber", student.getRegistrationNumber()));
        }
        if (details.containsKey("semester")) {
            student.setSemester(details.getOrDefault("semester", student.getSemester()));
        }

        if (details.containsKey("internalMarks")) {
            student.setInternalMarks(details.getOrDefault("internalMarks", "updated soon"));
        }

        if (details.containsKey("notesStatus")) {
            student.setNotesStatus(details.getOrDefault("notesStatus", "updated soon"));
        }
        if (details.containsKey("duesStatus")) {
            student.setDuesStatus(details.getOrDefault("duesStatus", "updated soon"));
        }
        if (details.containsKey("timetableStatus")) {
            student.setTimetableStatus(details.getOrDefault("timetableStatus", "updated soon"));
        }
        if (details.containsKey("semesterMarksStatus")) {
            student.setSemesterMarksStatus(details.getOrDefault("semesterMarksStatus", "updated soon"));
        }

        studentRepository.save(student);
        return ResponseEntity.ok(student);
    }
}
