package com.tuition.student_report_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tuition.student_report_system.model.TestMark;
import com.tuition.student_report_system.repository.TestMarkRepository;

@Service
public class TestMarkService {

    private final TestMarkRepository testMarkRepository;

    public TestMarkService(TestMarkRepository testMarkRepository) {
        this.testMarkRepository = testMarkRepository;
    }

    public List<TestMark> getAll(String search) {
        if (search != null && !search.isBlank()) {
            // search by name or roll number
            var byName = testMarkRepository.findByNameContainingIgnoreCase(search);
            var byRoll = testMarkRepository.findByRollNumberContainingIgnoreCase(search);
            byRoll.stream()
                  .filter(tm -> byName.stream().noneMatch(x -> x.getId().equals(tm.getId())))
                  .forEach(byName::add);
            return byName;
        }
        return testMarkRepository.findAll();
    }

    public TestMark create(TestMark testMark) {
        return testMarkRepository.save(testMark);
    }

    public TestMark update(String id, TestMark updated) {
        TestMark existing = testMarkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test mark not found"));
        existing.setName(updated.getName());
        existing.setRollNumber(updated.getRollNumber());
        existing.setYear(updated.getYear());
        existing.setSemester(updated.getSemester());
        existing.setBatch(updated.getBatch());
        existing.setMarks(updated.getMarks());
        existing.setDetails(updated.getDetails());
        return testMarkRepository.save(existing);
    }

    public void delete(String id) {
        if (!testMarkRepository.existsById(id)) {
            throw new RuntimeException("Test mark not found");
        }
        testMarkRepository.deleteById(id);
    }
}