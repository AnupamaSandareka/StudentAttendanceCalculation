package com.myproject.StudentManagemetSystem.service.StudentServiceImpl;

import com.myproject.StudentManagemetSystem.entiry.Subject;
import com.myproject.StudentManagemetSystem.repository.SubjectRepo;
import com.myproject.StudentManagemetSystem.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepo subjectRepo;

    @Override
    public String addSubject(Subject subject) {
        if(!subjectRepo.existsById(subject.getSubId())){
            subjectRepo.save(subject);
            return "Subject saved successfully!";
        }
        else {
            return "Subject already exist!";
        }
    }
}
