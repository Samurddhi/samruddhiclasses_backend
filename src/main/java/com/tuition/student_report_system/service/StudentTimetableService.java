// Backend/student-report-system/src/main/java/com/tuition/student_report_system/service/StudentTimetableService.java
package com.tuition.student_report_system.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tuition.student_report_system.dto.StudentTimetableSummaryDto;
import com.tuition.student_report_system.dto.UpdateCellRequest;
import com.tuition.student_report_system.model.DaySchedule;
import com.tuition.student_report_system.model.StudentTimetable;
import com.tuition.student_report_system.repository.StudentTimetableRepository;

@Service
public class StudentTimetableService {

    private final StudentTimetableRepository repository;
    private final com.tuition.student_report_system.repository.StudentRepository studentRepository;


    public StudentTimetableService(StudentTimetableRepository repository,
            com.tuition.student_report_system.repository.StudentRepository studentRepository) {
        this.repository = repository;
        this.studentRepository = studentRepository;
    }


    public List<StudentTimetableSummaryDto> listStudentsWithTimetables() {
        return repository.findAll().stream()
                .map(t -> new StudentTimetableSummaryDto(t.getStudentId(), t.getStudentName()))
                .collect(Collectors.toList());
    }

    public StudentTimetable getOrCreateDefaultTimetable(String studentId, String studentName) {
        return repository.findByStudentId(studentId).orElseGet(() -> {
            // default 5-day timetable with empty codes
            DaySchedule monday    = new DaySchedule("Monday",    "", "", "", "", "", "");
            DaySchedule tuesday   = new DaySchedule("Tuesday",   "", "", "", "", "", "");
            DaySchedule wednesday = new DaySchedule("Wednesday", "", "", "", "", "", "");
            DaySchedule thursday  = new DaySchedule("Thursday",  "", "", "", "", "", "");
            DaySchedule friday    = new DaySchedule("Friday",    "", "", "", "", "", "");

            StudentTimetable tt = new StudentTimetable(
                    studentId,
                    studentName,
                    List.of(monday, tuesday, wednesday, thursday, friday)
            );
            return repository.save(tt);
        });
    }

    public StudentTimetable getByStudentId(String studentId) {
        return repository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Timetable not found for studentId: " + studentId));
    }

    public StudentTimetable getMyTimetableByEmail(String email) {
        com.tuition.student_report_system.model.Student s = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found for email: " + email));
        return getByStudentId(s.getRegistrationNumber());
    }


    public StudentTimetable updateCell(String studentId, UpdateCellRequest request) {

        StudentTimetable tt = repository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Timetable not found for studentId: " + studentId));

        DaySchedule target = tt.getDays().stream()
                .filter(d -> d.getDay().equalsIgnoreCase(request.getDay()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Day not found: " + request.getDay()));

        String period = request.getPeriod();
        String value = request.getSubjectCode();

        switch (period.toUpperCase()) {
            case "P1": target.setP1(value); break;
            case "P2": target.setP2(value); break;
            case "P3": target.setP3(value); break;
            case "P4": target.setP4(value); break;
            case "P5": target.setP5(value); break;
            case "P6": target.setP6(value); break;
            default:
                throw new RuntimeException("Invalid period: " + period + ". Use P1..P6.");
        }

        return repository.save(tt);
    }
}