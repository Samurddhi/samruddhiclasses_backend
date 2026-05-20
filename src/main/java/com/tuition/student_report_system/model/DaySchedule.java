// Backend/student-report-system/src/main/java/com/tuition/student_report_system/model/DaySchedule.java
package com.tuition.student_report_system.model;

public class DaySchedule {

    private String day;   // e.g. "Monday"
    private String p1;    // period I
    private String p2;    // period II
    private String p3;    // period III
    private String p4;    // period IV
    private String p5;    // period V
    private String p6;    // period VI

    public DaySchedule() {}

    public DaySchedule(String day, String p1, String p2, String p3, String p4, String p5, String p6) {
        this.day = day;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.p6 = p6;
    }

    public String getDay() { return day; }
    public void setDay(String day) { this.day = day; }

    public String getP1() { return p1; }
    public void setP1(String p1) { this.p1 = p1; }

    public String getP2() { return p2; }
    public void setP2(String p2) { this.p2 = p2; }

    public String getP3() { return p3; }
    public void setP3(String p3) { this.p3 = p3; }

    public String getP4() { return p4; }
    public void setP4(String p4) { this.p4 = p4; }

    public String getP5() { return p5; }
    public void setP5(String p5) { this.p5 = p5; }

    public String getP6() { return p6; }
    public void setP6(String p6) { this.p6 = p6; }
}