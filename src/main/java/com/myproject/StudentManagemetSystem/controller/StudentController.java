package com.myproject.StudentManagemetSystem.controller;

import com.myproject.StudentManagemetSystem.dto.LoginRequestDTO;
import com.myproject.StudentManagemetSystem.entiry.Student;
import com.myproject.StudentManagemetSystem.entiry.Subject;
import com.myproject.StudentManagemetSystem.service.StudentAttendanceService;
import com.myproject.StudentManagemetSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentAttendanceService studentAttendanceService;

    @PostMapping(path = "/addStudent")
    String addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @GetMapping("/get-total-attendance/{studentId}")
    public Map<Subject, Double> getTotalAttendance(@PathVariable int studentId) {
        return studentAttendanceService.getTotalAttendancePercentageBySubject(studentId);
    }

    @PostMapping(path = "/login")
    public String login(@RequestBody LoginRequestDTO loginRequestDTO){
        return studentService.login(loginRequestDTO);
    }
}
