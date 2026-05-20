package com.tuition.student_report_system.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tuition.student_report_system.dto.StudentNoteDto;
import com.tuition.student_report_system.model.StudentNote;
import com.tuition.student_report_system.repository.StudentNoteRepository;
import com.tuition.student_report_system.repository.StudentRepository;

@Service
public class NotesService {

    private final StudentNoteRepository noteRepository;
    private final StudentRepository studentRepository;

    public NotesService(StudentNoteRepository noteRepository, StudentRepository studentRepository) {
        this.noteRepository = noteRepository;
        this.studentRepository = studentRepository;
    }

    public List<StudentNoteDto> getNotesByStudentId(String studentId) {
        validateStudentExists(studentId);
        List<StudentNote> notes = noteRepository.findByStudentId(studentId);
        return notes.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public StudentNote uploadNote(String studentId, String title, MultipartFile file) {
        validateStudentExists(studentId);
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        if (title == null || title.trim().isEmpty()) {
            title = file.getOriginalFilename();
        }

        try {
            String base64Data = Base64.getEncoder().encodeToString(file.getBytes());
            StudentNote note = new StudentNote(
                studentId,
                title,
                file.getOriginalFilename(),
                file.getContentType(),
                base64Data,
                file.getSize()
            );
            return noteRepository.save(note);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    public StudentNoteDto updateNote(String noteId, String title) {
        StudentNote note = noteRepository.findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));
        note.setTitle(title);
        noteRepository.save(note);
        return toDto(note);
    }

    public void deleteNote(String noteId) {
        if (!noteRepository.existsById(noteId)) {
            throw new IllegalArgumentException("Note not found");
        }
        noteRepository.deleteById(noteId);
    }

    public StudentNote getNoteById(String noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));
    }

    private void validateStudentExists(String studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new IllegalArgumentException("Student not found");
        }
    }

    private StudentNoteDto toDto(StudentNote note) {
        return new StudentNoteDto(
            note.getId(),
            note.getStudentId(),
            note.getTitle(),
            note.getFilename(),
            note.getContentType(),
            note.getUploadDate(),
            note.getSize()
        );
    }
}
