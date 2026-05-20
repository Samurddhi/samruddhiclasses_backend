package com.tuition.student_report_system.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "student_notes")
public class StudentNote {

    @Id
    private String id;

    private String studentId;

    private String title;

    private String filename;

    private String contentType;

    private String fileData;  // base64 encoded file content

    @Field("upload_date")
    private LocalDateTime uploadDate;

    private long size;  // file size in bytes

    public StudentNote() {}

    public StudentNote(String studentId, String title, String filename, String contentType, String fileData, long size) {
        this.studentId = studentId;
        this.title = title;
        this.filename = filename;
        this.contentType = contentType;
        this.fileData = fileData;
        this.uploadDate = LocalDateTime.now();
        this.size = size;
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

    public String getFileData() { return fileData; }
    public void setFileData(String fileData) { this.fileData = fileData; }

    public LocalDateTime getUploadDate() { return uploadDate; }
    public void setUploadDate(LocalDateTime uploadDate) { this.uploadDate = uploadDate; }

    public long getSize() { return size; }
    public void setSize(long size) { this.size = size; }
}
