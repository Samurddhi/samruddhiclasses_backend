// Backend/student-report-system/src/main/java/com/tuition/student_report_system/model/StudentTimetable.java
package com.tuition.student_report_system.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student_timetables")
public class StudentTimetable {

    @Id
    private String id;

    private String studentId;    // could be roll number or user id
    private String studentName;  // name to show in UI

    private List<DaySchedule> days;

    public StudentTimetable() {}

    public StudentTimetable(String studentId, String studentName, List<DaySchedule> days) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.days = days;
    }

    public String getId() { return id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public List<DaySchedule> getDays() { return days; }
    public void setDays(List<DaySchedule> days) { this.days = days; }
}