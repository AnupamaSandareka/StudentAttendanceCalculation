package com.myproject.StudentManagemetSystem.repository;

import com.myproject.StudentManagemetSystem.entiry.Student;
import com.myproject.StudentManagemetSystem.entiry.StudentAttendance;
import com.myproject.StudentManagemetSystem.entiry.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;


public interface StudentAttendanceRepo extends JpaRepository<StudentAttendance, Integer> {

    boolean existsByStudentAndSubjectAndInTimeIsNotNullAndOutTimeIsNullAndInTimeAfter(Student student, Subject subject, LocalDateTime localDateTime);

    boolean existsByStudentAndSubjectAndInTimeIsNotNullAndOutTimeIsNullAndInTimeBefore(Student student, Subject subject, LocalDateTime localDateTime);

    Optional<Object> findTopByStudentAndSubjectAndOutTimeIsNullOrderByInTimeDesc(Student student, Subject subject);
}
