package com.myproject.StudentManagemetSystem.repository;

import com.myproject.StudentManagemetSystem.entiry.DurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DurationRepository extends JpaRepository<DurationEntity, Long> {

    @Query("SELECT COALESCE(SUM(d.minutes + d.hours * 60), 0) FROM DurationEntity d WHERE d.studentAttendance.id = :attendanceId")
    long calculateTotalMinutes(@Param("attendanceId") Long attendanceId);

    @Query("SELECT sa.subject, COALESCE(SUM(d.attendancePercentage), 0) FROM DurationEntity d " +
            "JOIN d.studentAttendance sa " +
            "WHERE sa.student.stdId = :studentId " +
            "GROUP BY sa.subject")
    List<Object[]> getTotalAttendanceBySubject(@Param("studentId") int studentId);
}
