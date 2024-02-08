package com.myproject.StudentManagemetSystem.controller;

import com.myproject.StudentManagemetSystem.service.StudentAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/studentAttendance")
public class StudentAttendanceController {

    @Autowired
    private StudentAttendanceService studentAttendanceService;

    @PostMapping(
            path = "/markInTime/{studentId}",
            params = "subjectId"
    )
    public void markInTime(@PathVariable int studentId, @RequestParam String subjectId) {
        studentAttendanceService.markInTime(studentId, subjectId);
    }

    @PostMapping(
            path = "/markOutTime/{studentId}",
            params = "subjectId")
    public void markOutTime(@PathVariable int studentId, @RequestParam String subjectId) {
        studentAttendanceService.markOutTime(studentId, subjectId);
    }
}
