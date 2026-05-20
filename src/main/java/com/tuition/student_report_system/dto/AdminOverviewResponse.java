// Backend/.../dto/AdminOverviewResponse.java
package com.tuition.student_report_system.dto;

import java.util.List;

import com.tuition.student_report_system.model.Student;

public class AdminOverviewResponse {

    private long totalStudents;
    private List<StudentInfo> students;

    public AdminOverviewResponse(long totalStudents, List<StudentInfo> students) {
        this.totalStudents = totalStudents;
        this.students = students;
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public List<StudentInfo> getStudents() {
        return students;
    }

    public void setStudents(List<StudentInfo> students) {
        this.students = students;
    }

    public static class StudentInfo {
        private String id;
        private String name;
        private String email;
        private String registrationNumber;
        private String semester;

        public StudentInfo() {
        }

        public StudentInfo(String id, String name, String email, String registrationNumber, String semester) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.registrationNumber = registrationNumber;
            this.semester = semester;
        }

        public static StudentInfo fromStudent(Student student) {
            return new StudentInfo(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getRegistrationNumber(),
                student.getSemester()
            );
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
    }
}