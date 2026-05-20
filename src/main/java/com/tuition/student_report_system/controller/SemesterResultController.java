package com.tuition.student_report_system.controller;

import com.tuition.student_report_system.model.SemesterResult;
import com.tuition.student_report_system.service.SemesterResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/semester-results")
@CrossOrigin(origins = "*")
public class SemesterResultController {

    private final SemesterResultService service;

    public SemesterResultController(SemesterResultService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<SemesterResult>> getAll(@RequestParam(required = false) String search) {
        return ResponseEntity.ok(service.getAll(search));
    }

    @PostMapping
    public ResponseEntity<SemesterResult> create(@RequestBody SemesterResult result) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SemesterResult> update(@PathVariable String id, @RequestBody SemesterResult result) {
        return ResponseEntity.ok(service.update(id, result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SemesterResult>> getByStudent(@PathVariable String studentId) {
        return ResponseEntity.ok(service.getByStudentId(studentId));
    }
}

