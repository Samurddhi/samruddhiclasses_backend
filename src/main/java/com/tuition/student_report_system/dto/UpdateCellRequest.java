// Backend/student-report-system/src/main/java/com/tuition/student_report_system/dto/UpdateCellRequest.java
package com.tuition.student_report_system.dto;

public class UpdateCellRequest {
    private String day;         // "Monday", "Tuesday", ...
    private String period;      // "P1".."P6"
    private String subjectCode; // e.g. "CS3452"

    public String getDay() { return day; }
    public void setDay(String day) { this.day = day; }

    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }

    public String getSubjectCode() { return subjectCode; }
    public void setSubjectCode(String subjectCode) { this.subjectCode = subjectCode; }
}