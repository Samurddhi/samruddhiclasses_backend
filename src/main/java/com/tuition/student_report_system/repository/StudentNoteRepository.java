package com.tuition.student_report_system.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tuition.student_report_system.model.StudentNote;

public interface StudentNoteRepository extends MongoRepository<StudentNote, String> {
    List<StudentNote> findByStudentId(String studentId);
    @Override
    void deleteById(String id);
}
