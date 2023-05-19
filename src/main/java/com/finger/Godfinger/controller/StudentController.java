package com.finger.Godfinger.controller;


import com.finger.Godfinger.dto.StudentResponse;
import com.finger.Godfinger.service.StudentService;
import com.finger.Godfinger.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hammer")

public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/ham")
    public StudentResponse addStud(@RequestBody Student student) {
        return studentService.addStudent(student);

    }


    @GetMapping("/get/{id}")

    public StudentResponse addStud(@PathVariable("id") Long id) {
        return studentService.finById(id);

    }
    @GetMapping("/geta")

    public StudentResponse addStuda(@RequestParam Long id) {
        return studentService.finById(id);

    }
    @GetMapping("/all")
    public StudentResponse findAll(){
        return studentService.findAll();
    }


}