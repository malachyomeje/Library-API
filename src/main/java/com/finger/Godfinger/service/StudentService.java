package com.finger.Godfinger.service;

import com.finger.Godfinger.dto.StudentResponse;
import com.finger.Godfinger.model.Student;

public interface StudentService {
    StudentResponse addStudent(Student student);

    StudentResponse finById(Long id);

    StudentResponse findAll();
}
