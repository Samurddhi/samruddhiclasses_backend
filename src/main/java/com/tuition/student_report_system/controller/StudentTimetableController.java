package com.tuition.student_report_system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuition.student_report_system.dto.StudentTimetableSummaryDto;
import com.tuition.student_report_system.dto.UpdateCellRequest;
import com.tuition.student_report_system.model.StudentTimetable;
import com.tuition.student_report_system.service.StudentTimetableService;

@RestController
@RequestMapping("/api/timetables")
@CrossOrigin(origins = "*")
public class StudentTimetableController {

    private final StudentTimetableService timetableService;

    public StudentTimetableController(StudentTimetableService timetableService) {
        this.timetableService = timetableService;
    }

    // 1) list all students who have timetables
    @GetMapping("/students")
    public ResponseEntity<List<StudentTimetableSummaryDto>> listStudents() {
        return ResponseEntity.ok(timetableService.listStudentsWithTimetables());
    }

    // 2) get timetable for one student (admin)
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentTimetable> getByStudent(@PathVariable String studentId) {
        return ResponseEntity.ok(timetableService.getByStudentId(studentId));
    }

    // 3) get timetable for logged-in student
    @GetMapping("/me")
    public ResponseEntity<?> getMyTimetable(Authentication authentication) {

        // DEBUG LOGS
        System.out.println("Authentication: " + authentication);

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("User not authenticated");
        }

        String email = authentication.getName();

        System.out.println("Logged in user email: " + email);

        StudentTimetable timetable =
                timetableService.getMyTimetableByEmail(email);

        return ResponseEntity.ok(timetable);
    }

    // optional: create default timetable
    @PostMapping("/{studentId}/ensure")
    public ResponseEntity<StudentTimetable> ensureTimetable(
            @PathVariable String studentId,
            @RequestParam String studentName) {

        StudentTimetable tt =
                timetableService.getOrCreateDefaultTimetable(studentId, studentName);

        return ResponseEntity.ok(tt);
    }

    // 4) update single cell
    @PutMapping("/{studentId}/cell")
    public ResponseEntity<StudentTimetable> updateCell(
            @PathVariable String studentId,
            @RequestBody UpdateCellRequest request) {

        StudentTimetable updated =
                timetableService.updateCell(studentId, request);

        return ResponseEntity.ok(updated);
    }
}