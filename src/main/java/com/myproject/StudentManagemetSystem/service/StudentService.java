package com.myproject.StudentManagemetSystem.service;

import com.myproject.StudentManagemetSystem.dto.LoginRequestDTO;
import com.myproject.StudentManagemetSystem.entiry.Student;

public interface StudentService {
    String addStudent(Student student);


    String login(LoginRequestDTO loginRequestDTO);
}
