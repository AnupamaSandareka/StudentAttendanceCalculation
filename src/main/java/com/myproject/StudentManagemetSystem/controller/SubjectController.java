package com.myproject.StudentManagemetSystem.controller;

import com.myproject.StudentManagemetSystem.entiry.Subject;
import com.myproject.StudentManagemetSystem.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping(path = "/addSubject")
    public String addSubject(@RequestBody Subject subject){
        return subjectService.addSubject(subject);
    }

}
