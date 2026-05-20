package com.tuition.student_report_system.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.tuition.student_report_system.model.Student;
import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {
    Optional<Student> findByEmail(String email);
    // count() is already provided by MongoRepository
}
