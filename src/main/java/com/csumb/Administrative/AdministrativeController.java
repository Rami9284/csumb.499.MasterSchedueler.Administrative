package com.csumb.Administrative;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.csumb.Administrative.entities.Class;
import com.csumb.Administrative.entities.Student;
import com.csumb.Administrative.entities.Teacher;
import com.csumb.Administrative.repositotries.IClassRepository;
import com.csumb.Administrative.repositotries.IStudentRepository;
import com.csumb.Administrative.repositotries.ITeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdministrativeController{

    @Autowired
    IStudentRepository studentRepo;

    @Autowired
    ITeacherRepository teacherRepo;

    @Autowired
    IClassRepository classRepo;

    //Response : list of every student
    @CrossOrigin(origins = "*")
    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    // need to refactor this
    @CrossOrigin(origins = "*")
    @GetMapping("/findstudent/{id}")
    public Optional<Student> findStudent(@PathVariable String id) {
        return studentRepo.findById(id);
    }

    //Response : null if success,
    //           List of students with failures
    @CrossOrigin(origins = "*")
    @PostMapping("/addstudents")
    public List<Student> addStudents(@RequestBody List<Student> students) {
        List<Student> error = new ArrayList<>();
        Student err;

            for (Student s: students) {
                err = s;
                try {
                    studentRepo.insert(s);
                }
                catch (Exception e){
                    error.add(err);
                }
            }
        if(error.isEmpty()){
            return null;
        }
        return error;
    }

    //Response: list of students
    @CrossOrigin(origins = "*")
    @PutMapping("/updatestudents")
    public List<Student> updateStudents(@RequestBody List<Student> students){
        return studentRepo.saveAll(students);
    }

//    @CrossOrigin(origins = "*")
//    @DeleteMapping("/deletestudents")
//    public void deleteStudents(@RequestBody List<Student> students){
//        System.out.println("hello");
//        for (Student e: students) {
//            try {
//                System.out.printf("Trying to delete");
//                studentRepo.deleteById(e.getPer_id());
//            } catch (Exception ex) {
//                System.out.println("in catch");
//                System.out.println(ex);
//            }
//        }
//        System.out.println(students);
//        System.out.println(studentRepo.findAll());
//    }

    //Teachers
    //Response: list of all
    @CrossOrigin(origins = "*")
    @GetMapping("/teachers")
    public List<Teacher> getTeachers() {
        return teacherRepo.findAll();
    }

    // need to refactor this
    @CrossOrigin(origins = "*")
    @GetMapping("/findteacher/{id}")
    public Optional<Teacher> findTeacher(@PathVariable String id) {
        return teacherRepo.findById(id);
    }

    //Response : null if success,
    //           List of Teachers with failures
    @CrossOrigin(origins = "*")
    @PostMapping("/addteachers")
    public List<Teacher> addTeacher(@RequestBody List<Teacher> teachers) {
        List<Teacher> error = new ArrayList<>();
        Teacher err;

        for (Teacher s: teachers) {
            err = s;
            try {
                teacherRepo.insert(s);
            }
            catch (Exception e){
                error.add(err);
            }
        }

        if(error.isEmpty()){
            return null;
        }
        return error;
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/updateteachers")
    public List<Teacher> updateTeachers(@RequestBody List<Teacher> teachers){
        return teacherRepo.saveAll(teachers);
    }

//    @DeleteMapping("/{teachers}")
//    public void deleteTeacher(@RequestBody List<Teacher> teachers){
//        teacherRepo.deleteAll(teachers);
//    }
//

    //Classes
    //Response: list of all the classes
    @CrossOrigin(origins = "*")
    @GetMapping("/classes")
    public List<Class> getClasses() {
        return classRepo.findAll();
    }

    // need to refactor this
    @CrossOrigin(origins = "*")
    @GetMapping("/findclass/{id}")
    public Optional<Class> findClass(@PathVariable String id) {
        return classRepo.findById(id);
    }

    //Response : null if success,
    //           List of Teachers with failures
    @CrossOrigin(origins = "*")
    @PostMapping("/addclasses")
    public List<Class> addClasses(@RequestBody List<Class> classes) {
        List<Class> error = new ArrayList<>();
        Class err;
        for (Class s: classes) {
            err = s;
            try {
                classRepo.insert(s);
            }
            catch (Exception e){
                error.add(err);
            }
        }
        if(error.isEmpty()){
            return null;
        }
        return error;
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/updateclass")
    public List<Class> updateClasses(@RequestBody List<Class> classes){
        return classRepo.saveAll(classes);
    }

//    @DeleteMapping("/{classes}")
//    public void deleteClasses(@RequestBody List<Class> classes){
//        classRepo.deleteAll(classes);
//    }
//
  
}