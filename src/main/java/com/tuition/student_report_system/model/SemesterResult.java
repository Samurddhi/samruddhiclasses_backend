package com.tuition.student_report_system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Map;
import java.util.HashMap;

@Document(collection = "semester_results")
public class SemesterResult {

    @Id
    private String id;

    private String studentId;
    private String semester; // "I Sem", "II Sem", etc.
    private String examMonth; // "Dec 2024", "May 2025"
    private Map<String, Float> subjects = new HashMap<>(); // "Maths": 85.5
    private Float total;
    private String grade;

    public SemesterResult() {}

    public SemesterResult(String studentId, String semester, String examMonth) {
        this.studentId = studentId;
        this.semester = semester;
        this.examMonth = examMonth;
    }

    // getters & setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public String getExamMonth() { return examMonth; }
    public void setExamMonth(String examMonth) { this.examMonth = examMonth; }

    public Map<String, Float> getSubjects() { return subjects; }
    public void setSubjects(Map<String, Float> subjects) { this.subjects = subjects; }

    public Float getTotal() { return total; }
    public void setTotal(Float total) { this.total = total; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}

