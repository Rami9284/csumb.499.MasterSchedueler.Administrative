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

    //========================STUDENT====================================

    /*
    required: NA
    response: List of all students present
    */
    @CrossOrigin(origins = "*")
    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    /*
    required: String variable of student ID
    response: success -> Student with given ID
              failure -> Empty Student
    */
    @CrossOrigin(origins = "*")
    @GetMapping("/findstudent/{id}")
    public Student findStudent(@PathVariable String id) {
        return studentRepo.findById(id).orElse(new Student());  
    }

    /*
    required: List of students
    response: success -> null
              failure -> list of students not added to db
    */
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

    /*
    required: Student object
    response: success -> null
              failure -> student object
    */
    @CrossOrigin(origins = "*")
    @PostMapping("/addstudent")
    public Student addStudent(@RequestBody Student student) {
        try {
            studentRepo.insert(student);
        }
        catch (Exception e){
            return student;
        }
        return null; 
    }


    /*
    required: Student object
    response: success -> null
              failure -> error
    */
    @CrossOrigin(origins = "*")
    @PutMapping("/updatestudent")
    public Student updateStudent(@RequestBody Student student){
        return studentRepo.save(student);
    }

    /*
    required: Student object
    response: success -> null
              failure -> student object
    */
   @CrossOrigin(origins = "*")
   @DeleteMapping("/deletestudent")
   public Student deleteStudent(@RequestBody Student student){
       try {
           studentRepo.deleteById(student.getId());
       } catch (Exception ex) {
           return student;
       }

       return null;
    }

   /*
    required: Student ID as a string
    response: success -> null
              failure -> string with error description
    */
    @CrossOrigin(origins = "*")
    @PostMapping("/deletestudentId/{id}")
    public String deleteStudent(@PathVariable String id){
        try {
            studentRepo.deleteById(id);
        } catch (Exception ex) {
            return "Could not delete";
        }

     return null;
    }

    //========================TEACHER====================================
    
    /*
    required: Nothing
    response: List of all teachers present
    */
    @CrossOrigin(origins = "*")
    @GetMapping("/teachers")
    public List<Teacher> getTeachers() {
        return teacherRepo.findAll();
    }

   /*
    required: String variable of teacher ID
    response: success -> Teacher with given ID
              failure -> Empty teacher
    */
    @CrossOrigin(origins = "*")
    @GetMapping("/findteacher/{id}")
    public Teacher findTeacher(@PathVariable String id) {
        return teacherRepo.findById(id).orElse(new Teacher());
    }

    /*
    required: List of teacher objects
    response: success -> null
              failure -> List of Teachers with failures
    */
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
    
    /*
    required: Teacher object
    response: success -> null
              failure -> Teacher object
    */
    @CrossOrigin(origins = "*")
    @PostMapping("/addteacher")
    public Teacher addTeacher(@RequestBody Teacher teacher) {
           try {
                teacherRepo.insert(teacher);
           }
           catch (Exception e){
                return teacher;
            }
        
        return null;
    }

    /*
    required: Teacher object
    response: Nothing
    */
    @CrossOrigin(origins = "*")
    @PutMapping("/updateteacher")
    public void updateTeacher(@RequestBody Teacher teacher){
        teacherRepo.save(teacher);
    }

    /*
    required: Teacher object
    response: success -> Nothing
              failure -> Nothing
    */
   @DeleteMapping("/deleteteacher")
   public void deleteTeacher(@RequestBody Teacher teacher){
        try {
            teacherRepo.deleteById(teacher.getId());
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
   }

    //========================CLASS=====================================

    /*
    required: NA
    response: success -> List of classes present
              failure -> NA
    */
    @CrossOrigin(origins = "*")
    @GetMapping("/classes")
    public List<Class> getClasses() {
        return classRepo.findAll();
    }

    /*
    required: String variable of class ID
    response: success -> Class with given ID
              failure -> Empty Class
    */
    @CrossOrigin(origins = "*")
    @GetMapping("/findclass/{id}")
    public Class findClass(@PathVariable String id) {
        System.out.println(id);
        return classRepo.findById(id).orElse(new Class());
    }

    /*
    required: List of classes
    response: success -> null
              failure -> List of classes not added
    */
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

    /*
    required: Class object
    response: success -> null
              failure -> Class object not added
    */
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

    /*
    required: Class object
    response: success -> null
              failure -> Class not added
    */
    @CrossOrigin(origins = "*")
    @PutMapping("/updateclass")
    public Class updateClass(@RequestBody Class c){
        return classRepo.save(c);
    }

    /*
    required: Class object
    response: success -> NA
              failure -> NA
    */
   @DeleteMapping("/deleteclass")
   public void deleteClass(@RequestBody Class c){
        try {
            classRepo.deleteById(c.getId());

        } catch (Exception ex) {
            System.out.println(ex);
        }
        
   }


    //========================SECTION====================================

    /*
    required: NA
    response: success -> List of present sections
              failure -> NA
    */
    @CrossOrigin(origins = "*")
    @GetMapping("/sections")
    public List<Section> getSections() {
        return sectionRepo.findAll();
    }

    /*
    required: String variable of section ID
    response: success -> Section with given ID
              failure -> Empty Section
    */
    @CrossOrigin(origins = "*")
    @GetMapping("/findsection/{id}")
    public Section findSection(@PathVariable String id) {
        return sectionRepo.findById(id).orElse(new Section());
    }

    /*
    required: List of sections
    response: success -> null
              failure -> List of sections not added
    */
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

    /*
    required: Section object
    response: success -> null
              failure -> Section object not added
    */
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

    /*
    required: Section object
    response: success -> null
              failure -> Error
    */
    @CrossOrigin(origins = "*")
    @PutMapping("/updatesection")
    public Section updateSection(@RequestBody Section section){
        return sectionRepo.save(section);
    }

    /*
    required: Section object
    response: success -> NA
              failure -> NA
    */
   @DeleteMapping("/deletesection")
   public void deleteSection(@RequestBody Section section){
        try {
            sectionRepo.deleteById(section.getId());
        } catch (Exception ex) {
            System.out.println(ex);
        }
   }



   // Added to prevent errors in test files
    public Object updateTeachers(List<Teacher> teacherData) {
        return null;
    }


    public Object updateStudents(List<Student> studentData) {
        return null;
    }

  
}
