package com.tuition.student_report_system.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tuition.student_report_system.model.TestMark;

public interface TestMarkRepository extends MongoRepository<TestMark, String> {

    List<TestMark> findByNameContainingIgnoreCase(String name);

    List<TestMark> findByRollNumberContainingIgnoreCase(String rollNumber);

}
