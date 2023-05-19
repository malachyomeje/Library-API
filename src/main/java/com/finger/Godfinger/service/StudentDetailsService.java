package com.finger.Godfinger.service;

import com.finger.Godfinger.dto.StudentDto;
import com.finger.Godfinger.dto.StudentResponse;
import com.finger.Godfinger.repository.StudentRepository;
import com.finger.Godfinger.model.Student;
import com.finger.Godfinger.model.StudentDetails;
import com.finger.Godfinger.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component

public class StudentDetailsService implements UserDetailsService,StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Student> student = studentRepository.findByEmail(email);
        return student.map(StudentDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));
    }
@Override
    public StudentResponse addStudent(Student student){
        Optional<Student>student1=studentRepository.findByEmail(student.getEmail());

        if (student1.isPresent()){
            return new StudentResponse<>("failed","email already existr","failed to execute");
        }
    student.setPassword(AppUtil.passwordEncoder().encode(student.getPassword()));
        studentRepository.save(student);
        return new StudentResponse<>("sucesss","saved well well",student);
    }
    @Override
    public StudentResponse finById(Long id){
        Optional<Student>student = studentRepository.findById(id);
        if(student.isPresent()){
            return new StudentResponse<>("success","id is present",student);
        }
        return new StudentResponse<>("failed","id not present",student);
    }

@Override
public StudentResponse findAll(){
    List<Student> allStudents = studentRepository.findAll();

    List<StudentDto> student = new ArrayList<>();
    for(Student omeje :allStudents){
        StudentDto malachy = StudentDto.builder()
                .id(omeje.getId())
                .name(omeje.getName())
                .email(omeje.getEmail())
                .role(omeje.getRole())

                .build();

        student.add(malachy);
    }
    System.out.println(allStudents);
    System.out.println(student);
    return new StudentResponse<>("success","id is present",student);
}

}
