package com.csumb.Administrative;

import java.util.*;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
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
              failure -> null
    */
    @CrossOrigin(origins = "*")
    @GetMapping("/findstudent/{id}")
    public Student findStudent(@PathVariable String id) {
        Optional<Student> s = studentRepo.findById(id);
        if(s.isPresent())
            return s.get();
        else
            return null;
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
    response: success -> Student
              failure -> null
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

    /*
    required: String of id
    response: success -> Student
              failure -> null
    */
    @CrossOrigin(origins = "*")
    @GetMapping("/updatePeriod/{id}/{period}/{classId}")
    public Student updateStudentSchedule(@PathVariable String id,
         @PathVariable int period, @PathVariable String classId){

        Optional<Student> s = studentRepo.findById(id);
        if(s.isPresent()){
            Student ans = s.get();
            //step 1 get current class
            String sectionId = ans.getScheduleId().get(period-1);
            Optional<Section> sectionOptional = sectionRepo.findById(sectionId);
            if(sectionOptional.isPresent()){
                Section section = sectionOptional.get();
                //Step 2 get the other class
                Optional<Section> sectionOptional2 = sectionRepo.findById(classId);
                if(sectionOptional2.isPresent()){
                    Section section1 = sectionOptional2.get();
                    //Step 3 get rid of student from previous class
                    List<Pair<String, String>> roster = section.getRoster();
                    if(roster.remove(Pair.of(ans.getId(),ans.getName())))
                        System.out.println("removed");
                    section.setRoster(roster);

                    //step 4 add student to next section
                    section1.addStudent(ans);

                    //step 5 change student schedule
                    ans.setPeriod(period, section1);
                    studentRepo.save(ans);
                }
            }
            return ans;
        }
        return null;
    }

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
              failure -> null
    */
    @CrossOrigin(origins = "*")
    @GetMapping("/findteacher/{id}")
    public Teacher findTeacher(@PathVariable String id) {
        Optional<Teacher> s = teacherRepo.findById(id);
        if(s.isPresent()) {
            return s.get();
        }
        else
            return null;
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
    public Teacher updateTeacher(@RequestBody Teacher teacher){

        Optional<Teacher> teacherToUpdate= teacherRepo.findById(teacher.getId());
        if(teacherToUpdate.isPresent()){
            Teacher t = teacherToUpdate.get();
            t.setAcademy(teacher.getAcademy());
            t.setClassName(teacher.getClassName());
            t.setClassName2(teacher.getClassName2());
            t.setClassName3(teacher.getClassName3());
            t.setCurrentNumStudent(teacher.getCurrentNumStudent());
            t.setDepartment(teacher.getDepartment());
            t.setMaxNumStudent(teacher.getMaxNumStudent());
            t.setName(teacher.getName());
            t.setPreferred_room(teacher.getPreferred_room());
            t.setPrep(teacher.getPrep());
            t.setSections(teacher.getSections());
            t.setIs80Percent(teacher.isIs80Percent());
            return teacherRepo.save(t);
        }else{
            return null;
        }
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
        return sectionRepo.findById(id).orElse(null);
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
    @DeleteMapping("/deleteStudentSection/{studentId}/{sectionId}")
    public void deleteStudentSection(@PathVariable String studentId, @PathVariable String sectionId){
        Optional<Section> sectionOptional = sectionRepo.findById(sectionId);
        Optional<Student> studentOptional = studentRepo.findById(studentId);

        if(sectionOptional.isPresent() && studentOptional.isPresent()){
            Section section = sectionOptional.get();
            Student student = studentOptional.get();
            //Removed section from student schedule
            List<String> schedule = student.getSchedule();
            schedule.set(schedule.indexOf(section.getClassName()), "No Class");
            student.setSchedule(schedule);
            //Removing Student from section
            List<Pair<String, String>> roster = section.getRoster();
            roster.remove(Pair.of(student.getId(),student.getName()));
            section.setRoster(roster);

            sectionRepo.save(section);
            studentRepo.save(student);
        }

    }


    /*
    required: String of Section id
    response: success -> NA
              failure -> NA
    */
   @CrossOrigin(origins = "*")
   @DeleteMapping("/deletesectionId/{id}")
   public String deleteSection(@PathVariable String id){
        try {
            sectionRepo.deleteById(id);
        } catch (Exception ex) {
            return id;
        }

        return null;
   }

   //==============================OTHER============================
   /* Update Teacher to Section*/
   @CrossOrigin(origins = "*")
   @PostMapping("/setTeacherSection/{teacherId}/{sectionId}")
   public void TeacherToSection(@PathVariable String teacherId, @PathVariable String sectionId){
        Optional<Section> section = sectionRepo.findById(sectionId);
        Optional<Teacher> teacher = teacherRepo.findById(teacherId);

        if(section.isPresent() && teacher.isPresent()){
            Section s = section.get();
            Teacher t = teacher.get();

            t.addSection(s);
            for (Section sectionObj: t.getSections()) {
                if(sectionObj.getId().equals(s.getId())){
                    sectionObj.setRoster(s.getRoster());
                }
            }

            s.setTeacherID(teacherId);
            t.addSection(s);// Also adds students count in teacher

            sectionRepo.save(s);
            teacherRepo.save(t);


        }
   }

    /* delete Teacher from Section*/
    @CrossOrigin(origins = "*")
    @PostMapping("/removeTeacherSection/{teacherId}/{sectionId}")
    public void removeTeacherSection(@PathVariable String teacherId, @PathVariable String sectionId){
         Optional<Section> section = sectionRepo.findById(sectionId);
         Optional<Teacher> teacher = teacherRepo.findById(teacherId);

         int index = -1;

         if(section.isPresent() && teacher.isPresent()){
            Section s = section.get();
            Teacher t = teacher.get();

             System.out.println("Before"+ t.getSections().size());

             for (int i = 0; i < t.getSections().size(); i++) {
                 if(t.getSections().get(i).getId().equals(s.getId())){
                     index = i;
                     System.out.println("One to delete"+ t.getSections().get(i));
                 }
             }
             t.removeSection(t.getSections().get(index));
             System.out.println("after"+ t.getSections().size());

             s.setTeacherID("");
             //t.removeSection(s); // Also removes student count in teacher

             sectionRepo.save(s);
             teacherRepo.save(t);
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
    public List<Section> getSectionByClassName(@PathVariable String classname){
       return sectionRepo.findAllByClassName(classname);
   }
 /*update Class from Section*/

    @CrossOrigin(origins = "*")
    @PostMapping("/setClassSection/{classId}/{sectionId}")
    public void ClassToSection(@PathVariable String classId, @PathVariable String sectionId){
        Section s = sectionRepo.findById(sectionId).orElseThrow(null);
        Class c = classRepo.findById(classId).orElseThrow(null);

        if(s !=null && c !=null){
            s.setDepartment(c.getDepartment());
            s.setClassName(c.getClassName());
            s.setMaxNumSections(c.getMaxNumSections());
            s.setMaxNumStudentPerSection(c.getMaxNumStudentPerSection());
        }
    }
    /* delete Class from Section*/
    //refactor is needed
    @CrossOrigin(origins = "*")
    @PostMapping("/deletesectionfromclass/{classId}/{sectionId}")
    public void deleteSectionfromClass(@PathVariable String classId, @PathVariable String sectionId){
        Optional<Section> section = sectionRepo.findById(sectionId);
        Optional<Class> classs = classRepo.findById(classId);

        if(section.isPresent() && classs.isPresent()){
            Section s = section.get();
            
            s.setClassName("");
            sectionRepo.save(s);
        }

    }


}
