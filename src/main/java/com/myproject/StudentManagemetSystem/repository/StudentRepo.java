package com.myproject.StudentManagemetSystem.repository;

import com.myproject.StudentManagemetSystem.entiry.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

@EnableJpaRepositories
public interface StudentRepo extends JpaRepository<Student, Integer> {

    Student findByEmail(@Param("email") String email);
}
