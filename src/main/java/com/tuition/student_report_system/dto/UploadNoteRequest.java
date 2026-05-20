package com.tuition.student_report_system.dto;

public class UploadNoteRequest {
    private String title;

    public UploadNoteRequest() {}

    public UploadNoteRequest(String title) {
        this.title = title;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
