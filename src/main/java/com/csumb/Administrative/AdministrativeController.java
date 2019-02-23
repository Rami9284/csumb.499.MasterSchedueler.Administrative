package com.csumb.Administrative;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.csumb.Administrative.entities.Student;
import com.csumb.Administrative.entities.Teacher;
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

    //Response : list of every student
    @CrossOrigin(origins = "*")
    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentRepo.findAll();
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

    //Response: list of students
    @CrossOrigin(origins = "*")
    @PutMapping("/updatestudents")
    public List<Student> updateStudents(@RequestBody List<Student> students){
        return studentRepo.saveAll(students);
    }

    //Teachers
    //Response: list of all
    @CrossOrigin(origins = "*")
    @GetMapping("/teachers")
    public List<Teacher> getTeachers() {
        return teacherRepo.findAll();
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

    // need to refactor this
    @CrossOrigin(origins = "*")
    @GetMapping("/findteacher/{id}")
    public Optional<Teacher> findTeacher(@PathVariable String id) {
        return teacherRepo.findById(id);
    }

//    @DeleteMapping("/{teachers}")
//    public void deleteTeacher(@RequestBody List<Teacher> teachers){
//        teacherRepo.deleteAll(teachers);
//    }
//

    @CrossOrigin(origins = "*")
    @PutMapping("/updateteachers")
    public List<Teacher> updateTeachers(@RequestBody List<Teacher> teachers){
        return teacherRepo.saveAll(teachers);
    }
  
}