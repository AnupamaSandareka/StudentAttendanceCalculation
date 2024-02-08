package com.myproject.StudentManagemetSystem.entiry;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "studentAttendance")
public class StudentAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;


    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "in_time")
    private LocalDateTime inTime;

    @Column(name = "outTime")
    private LocalDateTime outTime;

    @OneToOne(mappedBy = "studentAttendance", cascade = CascadeType.ALL)
    private DurationEntity durationEntity;

    // Automatic timestamp generation for in-time before persisting
    @PrePersist
    public void prePersist() {
        this.inTime = LocalDateTime.now();
    }
}
