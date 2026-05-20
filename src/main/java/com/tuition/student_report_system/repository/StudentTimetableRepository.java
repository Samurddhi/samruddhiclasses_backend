// Backend/student-report-system/src/main/java/com/tuition/student_report_system/repository/StudentTimetableRepository.java
package com.tuition.student_report_system.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tuition.student_report_system.model.StudentTimetable;

public interface StudentTimetableRepository extends MongoRepository<StudentTimetable, String> {

    Optional<StudentTimetable> findByStudentId(String studentId);
}