package com.myproject.StudentManagemetSystem.entiry;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subject")
public class Subject {

    @Id
    @Column(name = "sub_id")
    private String subId;

    @Column(name = "sub_name")
    private String subName;

    @Column(name = "instructor_name")
    private String instructorName;

    @Column(name = "hours")
    private int hours;
}
