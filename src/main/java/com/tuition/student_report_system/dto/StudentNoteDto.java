package com.tuition.student_report_system.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StudentNoteDto {
    private String id;
    private String studentId;
    private String title;
    private String filename;
    private String contentType;
    private String formattedUploadDate;
    private long size;
    private String sizeFormatted;

    public StudentNoteDto() {}

    public StudentNoteDto(String id, String studentId, String title, String filename, 
                         String contentType, LocalDateTime uploadDate, long size) {
        this.id = id;
        this.studentId = studentId;
        this.title = title;
        this.filename = filename;
        this.contentType = contentType;
        this.formattedUploadDate = uploadDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm"));
        this.size = size;
        this.sizeFormatted = formatSize(size);
    }

    private String formatSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.1f KB", bytes / 1024.0);
        return String.format("%.1f MB", bytes / (1024.0 * 1024));
    }

    // getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }

    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }

    public String getFormattedUploadDate() { return formattedUploadDate; }
    public void setFormattedUploadDate(String formattedUploadDate) { this.formattedUploadDate = formattedUploadDate; }

    public long getSize() { return size; }
    public void setSize(long size) { this.size = size; }

    public String getSizeFormatted() { return sizeFormatted; }
    public void setSizeFormatted(String sizeFormatted) { this.sizeFormatted = sizeFormatted; }
}
