package com.myproject.StudentManagemetSystem.entiry;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "duration_table")
public class DurationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private Long hours;

    private Long minutes;

    @Column(name = "attendance_percentage")
    private Double attendancePercentage;

    @OneToOne
    @JoinColumn(name = "student_attendance_id")
    private StudentAttendance studentAttendance;
}

