package com.tuition.student_report_system.service;

import com.tuition.student_report_system.model.SemesterResult;
import com.tuition.student_report_system.repository.SemesterResultRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SemesterResultService {

    private final SemesterResultRepository repository;

    public SemesterResultService(SemesterResultRepository repository) {
        this.repository = repository;
    }

    public List<SemesterResult> getAll(String search) {
        if (search != null && !search.trim().isEmpty()) {
            // implement search if needed
        }
        return repository.findAll();
    }

    public SemesterResult create(SemesterResult result) {
        return repository.save(result);
    }

    public SemesterResult update(String id, SemesterResult result) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Result not found");
        }
        result.setId(id);
        return repository.save(result);
    }

    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Result not found");
        }
        repository.deleteById(id);
    }

    public List<SemesterResult> getByStudentId(String studentId) {
        return repository.findByStudentId(studentId);
    }

    public List<SemesterResult> getBySemester(String semester) {
        return repository.findBySemester(semester);
    }
}
