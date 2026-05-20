package com.tuition.student_report_system.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tuition.student_report_system.dto.StudentNoteDto;
import com.tuition.student_report_system.model.Student;
import com.tuition.student_report_system.model.StudentNote;
import com.tuition.student_report_system.repository.StudentRepository;
import com.tuition.student_report_system.service.NotesService;

@RestController
@RequestMapping("/api/admin/notes")
@CrossOrigin(origins = "*")
public class StudentNotesController {

    private final NotesService notesService;
    private final StudentRepository studentRepository;

    public StudentNotesController(NotesService notesService, StudentRepository studentRepository) {
        this.notesService = notesService;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{studentId}")
    public List<StudentNoteDto> getNotesByStudent(@PathVariable String studentId) {
        return notesService.getNotesByStudentId(studentId);
    }

    @PostMapping("/upload/{studentId}")
    public ResponseEntity<?> uploadNote(@PathVariable String studentId,
                                        @RequestParam("title") String title,
                                        @RequestParam("file") MultipartFile file) {
        try {
            notesService.uploadNote(studentId, title, file);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<StudentNoteDto> updateNote(@PathVariable String noteId,
                                                     @RequestBody String title) {
        try {
            StudentNoteDto updated = notesService.updateNote(noteId, title);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<?> deleteNote(@PathVariable String noteId) {
        try {
            notesService.deleteNote(noteId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

@GetMapping("/download/{noteId}")
    public ResponseEntity<ByteArrayResource> downloadNote(@PathVariable String noteId) {
        StudentNote note = notesService.getNoteById(noteId);
        byte[] data = Base64.getDecoder().decode(note.getFileData());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(note.getContentType()));
        headers.setContentDispositionFormData("attachment", note.getFilename());
        headers.setContentLength(data.length);

        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(data.length)
                .body(resource);
    }

@GetMapping("/preview/{noteId}")
    public ResponseEntity<ByteArrayResource> previewNote(@PathVariable String noteId) {
        StudentNote note = notesService.getNoteById(noteId);
        byte[] data = Base64.getDecoder().decode(note.getFileData());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(note.getContentType()));
        headers.set("Content-Disposition", "inline; filename=\"" + note.getFilename() + "\"");
        headers.setContentLength(data.length);

        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(data.length)
                .body(resource);
    }
}
