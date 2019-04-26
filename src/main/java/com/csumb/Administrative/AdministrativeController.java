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
import com.csumb.Administrative.seeders.ClassSeeder;
import com.csumb.Administrative.seeders.SectionSeeder;
import com.csumb.Administrative.seeders.StudentSeeder;
import com.csumb.Administrative.seeders.TeacherSeeder;
import com.sun.org.apache.xpath.internal.SourceTree;
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

    @Autowired
    private ClassSeeder classSeeder;

    @Autowired
    private SectionSeeder sectionSeeder;

    @Autowired
    private StudentSeeder studentSeeder;

    @Autowired
    private TeacherSeeder teacherSeeder;



    @CrossOrigin(origins = "*")
    @GetMapping("/reset")
    public void reset(){
        classSeeder.seed();
        sectionSeeder.seed();
        studentSeeder.seed();
        teacherSeeder.seed();
    }

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
        System.out.println(student);
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
        Optional<Student> studentToUpdate= studentRepo.findById(student.getId());
        if(studentToUpdate.isPresent()){
            Student s = studentToUpdate.get();
            s.setGrade(student.getGrade());
            s.setPreferred(student.getPreferred());
            s.setPreferredClasses(student.getPreferredClasses());
            s.setAcademy(student.getAcademy());
            return studentRepo.save(s);
        }else{
            return null;
        }
    }

//    @CrossOrigin(origins = "*")
//    @PutMapping("/updatestudentSchedule")
//    public Student updateStudentSchedule(@RequestBody Student student){
//
//
//    }

    /*
    required: String of id
    response: success -> null
              failure -> student object
    */
    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/deletestudentId/{id}")
    public String deleteS(@PathVariable String id){
        try {
            studentRepo.deleteById(id);
        } catch (Exception ex) {
            return id;
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
    required: String of teacher id
    response: success -> Nothing
              failure -> Nothing
    */
   @CrossOrigin(origins = "*")
   @DeleteMapping(path = "/deleteteacherId/{id}")
    public String deleteTeacher(@PathVariable String id){
        try {
            teacherRepo.deleteById(id);
        } catch (Exception ex) {
            return id;
        }
 
        return null;
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
    required: String of Class id
    response: success -> NA
              failure -> NA
    */
   @CrossOrigin(origins = "*")
   @DeleteMapping("/deleteclassId/{id}")
   public void deleteClass(@PathVariable String id){
        try {
            classRepo.deleteById(id);
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

    @CrossOrigin(origins = "*")
    @GetMapping("/sectionsForPer/{period}")
    public List<Section> sectionsForPer(@PathVariable int period){
        List<Section> sections = sectionRepo.findAllByPeriodNum(period);
        List<Section> ans = new ArrayList<>();
        Optional<Teacher> t;
        for(Section s: sections){
            if(s.canAddStudent()){
                t = teacherRepo.findById(s.getTeacherID());
                if(t.isPresent()) {
                    Teacher teacher = t.get();
                    if (teacher.getMaxNumStudent() >
                            teacher.getCurrentNumStudent()) {
                        ans.add(s);
                    }
                } else{
                    ans.add(s);
                }
            }
        }
        return ans;
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
        Optional<Section> sectionToUpdate = sectionRepo.findById(section.getId());
        if(sectionToUpdate.isPresent()){
            Section s = sectionToUpdate.get();
            s.setPeriodNum(section.getPeriodNum());
            s.setRoom(section.getRoom());
            return sectionRepo.save(section);
        }
        else{
            return null;
        }

    }

    @CrossOrigin(origins = "*")
    @PutMapping("/addStudentSection/{studentId}/{sectionId}")
    public void addStudentSection(@PathVariable String studentId, @PathVariable String sectionId){
        Section s = sectionRepo.findById(sectionId).orElseThrow(null);
        Student t = studentRepo.findById(studentId).orElseThrow(null);


        System.out.println("Inside function");
        if(s != null && t != null){
            if(s.canAddStudent(t) && t.isPeriodAvailable(s.getPeriodNum())){
                s.addStudent(t);
                t.setScheduleSection(s);
                System.out.println("Adding student");
                studentRepo.save(t);
                sectionRepo.save(s);
           }
        }
//        return sectionRepo.save(s);
//        return sectionRepo.save(section);

    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/deleteStudentSection/{studentId}/{sectionId}")
    public void deleteStudentSection(@PathVariable String studentId, @PathVariable String sectionId){
        Section s = sectionRepo.findById(sectionId).orElseThrow(null);
        Student t = studentRepo.findById(studentId).orElseThrow(null);


        System.out.println(s);
        System.out.println("Inside function");
        if(s != null && t != null){
            t.removeScheduleSection(s);
            s.removeStudent(t);
            System.out.println(s);
            System.out.println(t);
            System.out.println("Delete student");
            sectionRepo.save(s);
            studentRepo.save(t);

        }

//        return sectionRepo.save(section);

    }





    /*
    required: String of Section id
    response: success -> NA
              failure -> NA
    */
   @CrossOrigin(origins = "*")
   @DeleteMapping("/deletesectionId/{id}")
   public void deleteSection(@PathVariable String id){
        try {
            sectionRepo.deleteById(id);
        } catch (Exception ex) {
            System.out.println(ex);
        }
   }

   //==============================OTHER============================
   /* Update Teacher to Section*/
   @CrossOrigin(origins = "*")
   @PostMapping("/setTeacherSection/{teacherId}/{sectionId}")
   public void TeacherToSection(@PathVariable String teacherId, @PathVariable String sectionId){
        Section s = sectionRepo.findById(sectionId).orElseThrow(null);
        Teacher t = teacherRepo.findById(teacherId).orElseThrow(null);

        if(s != null && t != null){
            if(t.canAddSection(s)){
                s.setTeacherID(teacherId);
                t.addSection(s);// Also adds students count in teacher
            }
        }
   }

    /* delete Teacher from Section*/
    @CrossOrigin(origins = "*")
    @PostMapping("/removeTeacherSection/{teacherId}/{sectionId}")
    public void removeTeacherSection(@PathVariable String teacherId, @PathVariable String sectionId){
         Section s = sectionRepo.findById(sectionId).orElseThrow(null);
         Teacher t = teacherRepo.findById(teacherId).orElseThrow(null);
 
         if(s != null && t != null){
             s.setTeacherID("");
             t.removeSection(s); // Also removes student count in teacher
         }
    }

    public void updateClassSection(@RequestBody Class c, @PathVariable String sectionId){
        Section s = sectionRepo.findById(sectionId).orElseThrow(null);
        if(s != null){
            s.setDepartment(c.getDepartment());
            s.setClassName(c.getClassName());
            s.setMaxNumSections(c.getMaxNumSections());
            s.setMaxNumStudentPerSection(c.getMaxNumStudentPerSection());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getsectionbyclassname/{classname}")
    public List<Section> GetSectionByClassName(@PathVariable String classname){
       return sectionRepo.findAllByClassName(classname);
   }

}
