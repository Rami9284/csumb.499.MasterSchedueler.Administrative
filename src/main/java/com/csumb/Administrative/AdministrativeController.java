package com.csumb.Administrative;

import java.util.List;

import com.csumb.Administrative.entities.Student;
import com.csumb.Administrative.entities.Teacher;
import com.csumb.Administrative.repositotries.IStudentRepository;
import com.csumb.Administrative.repositotries.ITeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdministrativeController{

    @Autowired
    IStudentRepository studentRepo;
    ITeacherRepository teacherRepo;

    @GetMapping("/test")
    public String test() {
        
        return "This is a test";

    }



    //@CrossOrigin(origins = {"https://otterbuy.herokuapp.com","http://localhost:4200"})
    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentRepo.findAll();
    }
    
    @GetMapping("/teachers")
    public List<Teacher> getTeachers() {
        return teacherRepo.findAll();
    }

    @GetMapping("/teachers/{name}")
    public Student findStudent(@PathVariable String name) {
        return studentRepo.findByName(name);
    }

}