package com.myproject.StudentManagemetSystem.service.StudentServiceImpl;

import com.myproject.StudentManagemetSystem.dto.LoginRequestDTO;
import com.myproject.StudentManagemetSystem.entiry.Student;
import com.myproject.StudentManagemetSystem.repository.StudentRepo;
import com.myproject.StudentManagemetSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public String addStudent(Student student) {
        if(!studentRepo.existsById(student.getStdId())){
            studentRepo.save(student);
            return "Student details saved successfully";
        }
        return "Id already exist";
    }

    @Override
    public String login(LoginRequestDTO loginRequestDTO) {
        Student student = studentRepo.findByEmail(loginRequestDTO.getEmail());
        if(student.getPassword().equals(loginRequestDTO.getPassword())){
            return student.getRole();
        }
        else{
            return "invalid credentials.";
        }
    }
}
