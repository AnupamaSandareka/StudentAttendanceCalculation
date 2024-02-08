package com.myproject.StudentManagemetSystem.service;

import com.myproject.StudentManagemetSystem.entiry.Subject;

import java.util.Map;

public interface StudentAttendanceService {
    public void markInTime(int studentId, String subjectId);

    public void markOutTime(int studentId, String subjectId);

    Map<Subject, Double> getTotalAttendancePercentageBySubject(int studentId);
}
