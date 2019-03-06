package com.csumb.Administrative;

import java.util.ArrayList;
import java.util.List;

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
    //should not return duplicates 
    @CrossOrigin(origins = "*")
    @GetMapping("/findstudent/{id}")
    public Student findStudent(@PathVariable String id) {
        return studentRepo.findById(id).get();  
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
    @PutMapping("/updatestudent")
    public Student updateStudents(@RequestBody Student students){
        return studentRepo.save(students);
    }

    //Response : null if success,
    //           List of students with failures
   @CrossOrigin(origins = "*")
   @DeleteMapping("/deletestudent")
   public void deleteStudents(@RequestBody Student student){
           try {
               studentRepo.deleteById(student.getId());
           } catch (Exception ex) {
               System.out.println(ex);
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
    public Teacher findTeacher(@PathVariable String id) {
        return teacherRepo.findById(id).get();
    }

    //Response : null if success,
    //           List of Teachers with failures
    @CrossOrigin(origins = "*")
    @PostMapping("/addteacher")
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
    public void updateTeachers(@RequestBody Teacher teachers){
        teacherRepo.save(teachers);
    }

    //response: null if success
    //          List of techers with failure  
   @DeleteMapping("/deleteteacher")
   public void deleteTeacher(@RequestBody Teacher teacher){
        try {
            teacherRepo.deleteById(teacher.getId());
        } catch (Exception ex) {
            System.out.println(ex);
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
    public Class findClass(@PathVariable String id) {
        System.out.println(id);
        return classRepo.findById(id).orElse(new Class());
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
    public Class updateClasses(@RequestBody Class c){
        return classRepo.save(c);
    }

    //Response : null if success,
    //           List of Teachers with failures
   @DeleteMapping("/deleteclass")
   public void deleteClasses(@RequestBody Class c){
        try {
            classRepo.deleteById(c.getId());
        } catch (Exception ex) {
            System.out.println(ex);
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
    public Section findSection(@PathVariable String id) {
        return sectionRepo.findById(id).get();
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
    public Section updateSections(@RequestBody Section section){
        return sectionRepo.save(section);
    }

    //Response : null if success,
    //           List of Teachers with failures
   @DeleteMapping("/deletesections")
   public void deleteSections(@RequestBody Section section){
            try {
                sectionRepo.deleteById(section.getId());
            } catch (Exception ex) {
                System.out.println(ex);
            }
   }

    public Object updateTeachers(List<Teacher> teacherData) {
        return null;
    }

    public Object updateStudents(List<Student> studentData) {
        return null;
    }

  
}