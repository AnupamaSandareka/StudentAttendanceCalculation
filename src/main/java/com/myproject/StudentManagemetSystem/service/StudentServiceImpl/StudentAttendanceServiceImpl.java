package com.myproject.StudentManagemetSystem.service.StudentServiceImpl;

import com.myproject.StudentManagemetSystem.entiry.DurationEntity;
import com.myproject.StudentManagemetSystem.entiry.Student;
import com.myproject.StudentManagemetSystem.entiry.StudentAttendance;
import com.myproject.StudentManagemetSystem.entiry.Subject;
import com.myproject.StudentManagemetSystem.repository.DurationRepository;
import com.myproject.StudentManagemetSystem.repository.StudentAttendanceRepo;
import com.myproject.StudentManagemetSystem.repository.StudentRepo;
import com.myproject.StudentManagemetSystem.repository.SubjectRepo;
import com.myproject.StudentManagemetSystem.service.StudentAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentAttendanceServiceImpl implements StudentAttendanceService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private StudentAttendanceRepo studentAttendanceRepo;

    @Autowired
    private DurationRepository durationRepository;

    @Autowired
    private SubjectRepo subjectRepo;

    @Override
    public void markInTime(int studentId, String subjectId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student id"));

        Subject subject = subjectRepo.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid subject id"));

        // Check if an entry already exists for the current day
        if (!studentAttendanceRepo.existsByStudentAndSubjectAndInTimeIsNotNullAndOutTimeIsNullAndInTimeAfter(
                student, subject, LocalDateTime.now().withHour(0).withMinute(0).withSecond(0))) {
            StudentAttendance subjectAttendance = new StudentAttendance();
            subjectAttendance.setStudent(student);
            subjectAttendance.setSubject(subject);
            studentAttendanceRepo.save(subjectAttendance);
        }
    }

    @Override
    public void markOutTime(int studentId, String subjectId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student id"));

        Subject subject = subjectRepo.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid subject id"));

        // Check if an entry already exists for the current day
        if (!studentAttendanceRepo.existsByStudentAndSubjectAndInTimeIsNotNullAndOutTimeIsNullAndInTimeBefore(
                student, subject, LocalDateTime.now().withHour(0).withMinute(0).withSecond(0))) {
            // No existing entry for the current day, mark out time
            StudentAttendance subjectAttendance = (StudentAttendance) studentAttendanceRepo
                    .findTopByStudentAndSubjectAndOutTimeIsNullOrderByInTimeDesc(student, subject)
                    .orElseThrow(() -> new RuntimeException("No matching inTime entry found for the student"));

            subjectAttendance.setOutTime(LocalDateTime.now());
            studentAttendanceRepo.save(subjectAttendance);

            // Calculate and save the duration
            calculateAndSaveDuration(subjectAttendance);
        }
    }

    private void calculateAndSaveDuration(StudentAttendance studentAttendance) {
        if (studentAttendance.getInTime() != null && studentAttendance.getOutTime() != null) {
            LocalDateTime inTime = studentAttendance.getInTime();
            LocalDateTime outTime = studentAttendance.getOutTime();

            // Calculate the duration
            Duration duration = Duration.between(inTime, outTime);

            // Extract hours and minutes from the duration
            long hours = duration.toHours();
            long minutes = duration.toMinutesPart();

            // Save the duration to the new table
            DurationEntity durationEntity = new DurationEntity();
            durationEntity.setStudent(studentAttendance.getStudent());
            durationEntity.setHours(hours);
            durationEntity.setMinutes(minutes);

            // Set the association
            durationEntity.setStudentAttendance(studentAttendance);

            // You need to set the attendance percentage based on your logic
            double attendancePercentage = calculateAttendancePercentage(durationEntity);
            durationEntity.setAttendancePercentage(attendancePercentage);

            // Save the duration entity
            durationRepository.save(durationEntity);
        }
    }

    private double calculateAttendancePercentage(DurationEntity durationEntity) {
        if (durationEntity != null) {
            int totalSubjectHours = durationEntity.getStudentAttendance().getSubject().getHours();
            long totalMinutes = durationEntity.getHours() * 60 + durationEntity.getMinutes();
            double percentage = (totalMinutes / ((double) totalSubjectHours * 60)) * 100;
            return Math.min(percentage, 100.0);
        }
        return 0.0;
    }


    @Override
    public Map<Subject, Double> getTotalAttendancePercentageBySubject(int studentId) {
        List<Object[]> result = durationRepository.getTotalAttendanceBySubject(studentId);
        Map<Subject, Double> totalAttendanceMap = new HashMap<>();

        for (Object[] row : result) {
            Subject subject = (Subject) row[0];
            Double totalAttendance = (Double) row[1];

            // Format the total attendance percentage to two decimal points
            DecimalFormat df = new DecimalFormat("#.##");
            totalAttendance = Double.parseDouble(df.format(totalAttendance));

            totalAttendanceMap.put(subject, totalAttendance);
        }

        return totalAttendanceMap;
    }
}
