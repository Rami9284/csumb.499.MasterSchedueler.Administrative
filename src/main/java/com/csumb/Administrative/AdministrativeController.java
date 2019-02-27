package com.csumb.Administrative;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.csumb.Administrative.entities.Class;
import com.csumb.Administrative.entities.Section;
import com.csumb.Administrative.entities.Student;
import com.csumb.Administrative.entities.Teacher;
import com.csumb.Administrative.repositotries.IClassRepository;
import com.csumb.Administrative.repositotries.ISectionRepository;
import com.csumb.Administrative.repositotries.IStudentRepository;
import com.csumb.Administrative.repositotries.ITeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdministrativeController{

    @Autowired
    private IStudentRepository studentRepo;

    @Autowired
    private ITeacherRepository teacherRepo;

    @Autowired
    private IClassRepository classRepo;

    @Autowired
    private ISectionRepository sectionRepo;

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

    //Response : null if success,
    //           List of students with failures
   @CrossOrigin(origins = "*")
   @DeleteMapping("/deletestudents")
   public void deleteStudents(@RequestBody List<Student> students){
       for (Student e: students) {
           try {
               studentRepo.deleteById(e.getId());
           } catch (Exception ex) {
               System.out.println(ex);
           }
       }
   }

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

    //response: null if success
    //          List of techers with failure  
   @DeleteMapping("/deleteteachers")
   public void deleteTeacher(@RequestBody List<Teacher> teachers){
        for (Teacher e: teachers) {
            try {
                teacherRepo.deleteById(e.getId());
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
   }

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

    //Response : null if success,
    //           List of Teachers with failures
   @DeleteMapping("/deleteclasses")
   public void deleteClasses(@RequestBody List<Class> classes){
        for (Class e: classes) {
            try {
                classRepo.deleteById(e.getClass_id());
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
   }


    //Sections
    //Response: list of all the sections
    @CrossOrigin(origins = "*")
    @GetMapping("/sections")
    public List<Section> getSections() {
        return sectionRepo.findAll();
    }

    // need to refactor this
    @CrossOrigin(origins = "*")
    @GetMapping("/findsection/{id}")
    public Optional<Section> findSection(@PathVariable String id) {
        return sectionRepo.findById(id);
    }

    //Response : null if success,
    //           List of Sections with failures
    @CrossOrigin(origins = "*")
    @PostMapping("/addsections")
    public List<Section> addSections(@RequestBody List<Section> sections) {
        List<Section> error = new ArrayList<>();
        Section err;
        for (Section s: sections) {
            err = s;
            try {
                sectionRepo.insert(s);
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
    @PutMapping("/updatesection")
    public List<Section> updateSections(@RequestBody List<Section> sections){
        return sectionRepo.saveAll(sections);
    }

    //Response : null if success,
    //           List of Teachers with failures
   @DeleteMapping("/deletesections")
   public void deleteSections(@RequestBody List<Section> sections){
        for (Section e: sections) {
            try {
                sectionRepo.deleteById(e.getClass_id());
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
   }

  
}