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

    @CrossOrigin(origins = "*")
    @PostMapping("/addstudent")
    public Student addStudents(@RequestBody Student student) {
        try {
            studentRepo.insert(student);
        }
        catch (Exception e){
            return student;
        }
        return null; 
    }


    //Response: list of students
    @CrossOrigin(origins = "*")
    @PutMapping("/updatestudent")
    public Student updateStudent(@RequestBody Student student){
        return studentRepo.save(student);
    }

    //Response : null if success,
    //           List of students with failures
   @CrossOrigin(origins = "*")
   @DeleteMapping("/deletestudent/{id}")
   public void deleteStudent(@PathVariable String id){
           try {
               studentRepo.deleteById(id);
           } catch (Exception ex) {
               System.out.println("Error");
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
    @PostMapping("/addteachers")
    public List<Teacher> addTeachers(@RequestBody List<Teacher> teachers) {
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
    @PutMapping("/updateteacher")
    public void updateTeacher(@RequestBody Teacher teacher){
        teacherRepo.save(teacher);
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
        return classRepo.findById(id).get();
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
    @PostMapping("/addclass")
    public Class addClass(@RequestBody Class classs) {
        
            try {
                classRepo.insert(classs);
            }
            catch (Exception e){
                return classs;
            }
        
        return null;
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/updateclass")
    public Class updateClass(@RequestBody Class c){
        return classRepo.save(c);
    }

    //Response : null if success,
    //           List of Teachers with failures
   @DeleteMapping("/deleteclass")
   public void deleteClass(@RequestBody Class c){
        try {
            classRepo.deleteById(c.getid());
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

    //Response : null if success,
    //           List of Sections with failures
    @CrossOrigin(origins = "*")
    @PostMapping("/addsection")
    public Section addSection(@RequestBody Section section) {
        try {
            sectionRepo.insert(section);
        }
        catch (Exception e){
            return section;
        }
        
        return null;
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/updatesection")
    public Section updateSection(@RequestBody Section section){
        return sectionRepo.save(section);
    }

    //Response : null if success,
    //           List of Teachers with failures
   @DeleteMapping("/deletesection")
   public void deleteSection(@RequestBody Section section){
            try {
                sectionRepo.deleteById(section.getid());
            } catch (Exception ex) {
                System.out.println(ex);
            }
   }


    //Ignore these; they are to stop an error from ocurring   
    public Object updateTeachers(List<Teacher> teacherData) {
        return null;
    }

    public Object updateStudents(List<Student> studentData) {
        return null;
    }

  
}