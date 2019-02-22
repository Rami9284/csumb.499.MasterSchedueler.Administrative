package com.csumb.Administrative;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.csumb.Administrative.entities.Student;
import com.csumb.Administrative.repositotries.IStudentRepository;
import com.csumb.Administrative.repositotries.ITeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdministrativeController{

    @Autowired
    IStudentRepository studentRepo;
    ITeacherRepository teacherRepo;

    //@CrossOrigin(origins = {"https://otterbuy.herokuapp.com","http://localhost:4200"})
    @GetMapping("/students")
    public List<Student> getStudents() {
        //List<Student> result = studentRepo.getAll();
        //return result;

        List<Student> ans = new ArrayList<Student>(Arrays.asList(new Student("1"), new Student("2"), new Student("3")));
        return ans;
    }
    
    @GetMapping("/teachers")
    public List<Student> getTeachers() {
        //List<Student> result = studentRepo.getAll();
        //return result;

        return null;
    }

}