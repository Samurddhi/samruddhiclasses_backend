package com.tuition.student_report_system.dto;

public class RegisterRequest {

    private String email;
    private String password;
    private String role;
    private String name;
    private String registrationNumber;
    private String semester;

    // default constructor
    public RegisterRequest() {}

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
}
