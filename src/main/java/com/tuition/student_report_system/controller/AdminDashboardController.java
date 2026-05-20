// Backend/.../controller/AdminDashboardController.java
package com.tuition.student_report_system.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.tuition.student_report_system.dto.AdminOverviewResponse;
import com.tuition.student_report_system.repository.StudentRepository;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminDashboardController {

    private final StudentRepository studentRepository;

    public AdminDashboardController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/overview")
    public AdminOverviewResponse getOverview() {
      List<AdminOverviewResponse.StudentInfo> students = studentRepository.findAll()
          .stream()
          .map(AdminOverviewResponse.StudentInfo::fromStudent)
          .toList();

      return new AdminOverviewResponse(students.size(), students);
    }
}