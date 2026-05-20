package com.tuition.student_report_system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")
public class Student {

    @Id
    private String id;

    private String name;
    private String email;
    private String registrationNumber;
    private String semester;

    private String internalMarks;
    private String notesStatus;
    private String duesStatus;
    private String timetableStatus;
    private String semesterMarksStatus;

    public Student() {
        this.internalMarks = "updated soon";
        this.notesStatus = "updated soon";
        this.duesStatus = "updated soon";
        this.timetableStatus = "updated soon";
        this.semesterMarksStatus = "updated soon";
    }

    public Student(String name, String email, String registrationNumber, String semester) {
        this();
        this.name = name;
        this.email = email;
        this.registrationNumber = registrationNumber;
        this.semester = semester;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getInternalMarks() {
        return internalMarks;
    }

    public void setInternalMarks(String internalMarks) {
        this.internalMarks = internalMarks;
    }

    public String getNotesStatus() {
        return notesStatus;
    }

    public void setNotesStatus(String notesStatus) {
        this.notesStatus = notesStatus;
    }

    public String getDuesStatus() {
        return duesStatus;
    }

    public void setDuesStatus(String duesStatus) {
        this.duesStatus = duesStatus;
    }

    public String getTimetableStatus() {
        return timetableStatus;
    }

    public void setTimetableStatus(String timetableStatus) {
        this.timetableStatus = timetableStatus;
    }

    public String getSemesterMarksStatus() {
        return semesterMarksStatus;
    }

    public void setSemesterMarksStatus(String semesterMarksStatus) {
        this.semesterMarksStatus = semesterMarksStatus;
    }
}
