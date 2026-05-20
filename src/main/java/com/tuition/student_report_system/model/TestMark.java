package com.tuition.student_report_system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "test_marks")
public class TestMark {

    @Id
    private String id;

    private String name;
    private String rollNumber;
    private String year;      // e.g. "III"
    private String semester;  // e.g. "06"
    private String batch;     // e.g. "2020-2024"
    private String marks;     // e.g. "85/100"
    private String details;   // e.g. "Unit test + Mid term"

    // constructors
    public TestMark() {}

    public TestMark(String name, String rollNumber, String year, String semester, String batch, String marks, String details) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.year = year;
        this.semester = semester;
        this.batch = batch;
        this.marks = marks;
        this.details = details;
    }

    // getters & setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public String getBatch() { return batch; }
    public void setBatch(String batch) { this.batch = batch; }

    public String getMarks() { return marks; }
    public void setMarks(String marks) { this.marks = marks; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}