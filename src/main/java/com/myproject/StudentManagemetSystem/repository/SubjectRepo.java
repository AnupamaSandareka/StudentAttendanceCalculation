package com.myproject.StudentManagemetSystem.repository;

import com.myproject.StudentManagemetSystem.entiry.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepo extends JpaRepository<Subject, String> {
}
