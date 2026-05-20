package com.tuition.student_report_system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tuition.student_report_system.model.TestMark;
import com.tuition.student_report_system.service.TestMarkService;

@RestController
@RequestMapping("/api/test-marks")
@CrossOrigin(origins = "*")
public class TestMarkController {

    private final TestMarkService testMarkService;

    public TestMarkController(TestMarkService testMarkService) {
        this.testMarkService = testMarkService;
    }

    @GetMapping
    public ResponseEntity<List<TestMark>> getAll(
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(testMarkService.getAll(search));
    }

    @PostMapping
    public ResponseEntity<TestMark> create(@RequestBody TestMark testMark) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(testMarkService.create(testMark));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestMark> update(
            @PathVariable String id,
            @RequestBody TestMark testMark) {
        return ResponseEntity.ok(testMarkService.update(id, testMark));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        testMarkService.delete(id);
        return ResponseEntity.noContent().build();
    }
}