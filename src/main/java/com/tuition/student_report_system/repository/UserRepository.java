package com.tuition.student_report_system.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tuition.student_report_system.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);   // ✅ must match field name

}
