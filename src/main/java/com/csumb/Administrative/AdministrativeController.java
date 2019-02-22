package com.csumb.Administrative;

import java.util.List;

import com.csumb.Administrative.entities.Student;
import com.csumb.Administrative.entities.Teacher;
import com.csumb.Administrative.repositotries.IStudentRepository;
import com.csumb.Administrative.repositotries.ITeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    //Students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    @PostMapping(path = "/addStudent")
    public void addStudent(@RequestBody Student student) {
        studentRepo.insert(student);
    }

    @PostMapping(path = "/addStudents")
    public void addStudents(@RequestBody List<Student> student) {
        studentRepo.insert(student);
    }

    @DeleteMapping("/{student}")
    public void deleteStudent(@RequestBody Student student){
        studentRepo.delete(student);
    }

    @DeleteMapping("/{students}")
    public void deleteStudents(@RequestBody List<Student> student){
        studentRepo.deleteAll(student);
    }

    @PutMapping("/{userId}")
    public void updateStudent(@RequestBody Student student){
        studentRepo.save(student);
    } 
    
    @PutMapping("/{userId}")
    public void updateStudents(@RequestBody List<Student> student){
        studentRepo.saveAll(student);
    }
    

    //Teachers
    @GetMapping("/teachers")
    public List<Teacher> getTeachers() {
        return teacherRepo.findAll();
    }
    
    @PostMapping(path = "/addTeacher")
    public void addTeacher(@RequestBody Teacher teacher) {
        teacherRepo.insert(teacher);
    }

    @PostMapping(path = "/addteachers")
    public void addTeacher(@RequestBody List<Teacher> teacher) {
        teacherRepo.insert(teacher);
    }

    @DeleteMapping("/{teacher}")
    public void deleteTeacher(@RequestBody Teacher teacher){
        teacherRepo.delete(teacher);
    }

    @DeleteMapping("/{teachers}")
    public void deleteTeacher(@RequestBody List<Teacher> teachers){
        teacherRepo.deleteAll(teachers);
    }

    @PutMapping("/{userId}")
    public void updateTeacher(@RequestBody Teacher teacher){
        teacherRepo.save(teacher);
    } 
    
    @PutMapping("/{userId}")
    public void updateTeachers(@RequestBody List<Teacher> teacher){
        teacherRepo.saveAll(teacher);
    }
  
}