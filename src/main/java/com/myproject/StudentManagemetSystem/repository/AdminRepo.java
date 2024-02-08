package com.myproject.StudentManagemetSystem.repository;

import com.myproject.StudentManagemetSystem.entiry.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, Long> {
}
