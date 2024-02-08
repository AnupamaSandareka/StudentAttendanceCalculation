package com.myproject.StudentManagemetSystem.entiry;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "std_id")
    int stdId;

    @Column(name = "std_name")
    private String stdName;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentAttendance> attendanceRecords;
}
