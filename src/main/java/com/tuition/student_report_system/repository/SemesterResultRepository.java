package com.tuition.student_report_system.repository;

import com.tuition.student_report_system.model.SemesterResult;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface SemesterResultRepository extends MongoRepository<SemesterResult, String> {
    List<SemesterResult> findByStudentId(String studentId);
    List<SemesterResult> findBySemester(String semester);
}

