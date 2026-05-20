// Backend/student-report-system/src/main/java/com/tuition/student_report_system/dto/StudentTimetableSummaryDto.java
package com.tuition.student_report_system.dto;

public class StudentTimetableSummaryDto {
    private String studentId;
    private String studentName;

    public StudentTimetableSummaryDto() {}

    public StudentTimetableSummaryDto(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
}