MasterSchedueler.Administrative - README
===

# MasterSchedueler.Administrative

## Table of Contents
1. [Overview](#Overview)
2. [Product Spec](#Product-Spec)
3. [Schema](#Schema)
4. [Networking](#Networking)

## Overview
### Description
California State University Capstone Project, Salinas Union High School District Sponsored. Micro service for the Master Scheduler App.
The Master scheduler app is a tool implemented for the Administrative team. This app makes it easier for the admin team to generate the school schedule. 

This administrative micro services handels all of the insert, updates, and deletes request for the student, classes, teachers, and section objects. 
Micro service is deployed on Heroku.  https://master-scheduler-admin.herokuapp.com/

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**
  - [X] User can add students, teachers, classes, and sections
  - [X] User can update students, teachers, classes, and sections
  - [X] User can delete students, teachers, classes, and sections
  - [X] User can find student(s), teacher(s), class(es), and section(s)

**Optional Nice-to-have Stories**
  - [ ] User can download Schedule
  - [ ] User can upload .csv files to add students, teachers, classes, and sections

## Schema 

### Models
#### Student
|Property|Type|Description|
|---|---|---|
|id|String|Id of the student. Database Primary key|
|name|String|Name of student|
|grade|int|Grade of student|
|preferredClasses|List of String|List of classes student wants to take|
|preferred|List of Boolean|Maps to the classes student want to take and determines if a student has priority to take the class|
|academy|String|Student's academy they are part of [none, green, fast]|
|schedule|List of String|Student's schedule that was generated. A list of the class names|
|scheduleId|List of String|Student's schedule that was generated. A list of class Ids that map to class names|

#### Teacher
|Property|Type|Description|
|---|---|---|
|id|String|Id of the teacher. Database primary key|
|name|String|Name of the teacher|
|department|String|Department teacher is apart of|
|prep|int|Teacher's prep period|
|preferre_room|String|Room teacher wants to teach in|
|is80Percent|boolean|If true teacher teaches 5 sections. If false teacher teaches 4 sections.|
|academy|String|Teacher's academy they are part of [none, green, fast]|
|maxNumStudent|int|Maximum number of students the teacher can teach|
|currentNumStudent|int|Number of students teacher currently has|
|sections|List of Section|Teachers schedule|
|className|String|1rst class teacher can teach|
|className2|String|2nd class teacher can teach|
|className3|String|3rd class teacher can teach|

#### Class
|Property|Type|Description|
|---|---|---|
|id|String|Id of the Class. Database primary key|
|className|String|Name of the class|
|maxNumSections|int|Max number of sections that can be created form this class|
|maxNumStudentPerSection|int|Max number of students for each section|
|numStudentRegistered|int|how many students have registed for the class (in progress)|
    
#### Section extends Class
|Property|Type|Description|
|---|---|---|
|section_num|int|Section number. Section_num gets used with class id to create primary key|
|periodNum|int|Period when section will be held|
|roster|List of Par of string , string|List of student name and id that will take the class|
|teacherID|String|Teache ID who is teaching the class|
|room|String|how many students have registed for the class (in progress)|

## Networking
## List of network requests
### Students
- Get Students 
    - (Read/Get) Gets all of the students in the Database
    - Required: NA
    - Response: List of all students present
     ``` java
        @CrossOrigin(origins = "*")
        @GetMapping("/students")
        public List<Student> getStudents() {
            return studentRepo.findAll();
        }
    ```
- Find Student  
    - (Read/Get) Gets a student based on ID
    - Required: Student ID: String
    - Response: 
        - Success: Student
        - Fail: null
     ``` java
        @CrossOrigin(origins = "*")
        @GetMapping("/findstudent/{id}")
        public Student findStudent(@PathVariable String id) {
        // Implementation
        }
    ```

- Add Student  
    - (Insert/Post) Insert a student
    - Required: Student Object: Student
    - Response: 
        - Success: null
        - Fail: Student Object who count be inserted
     ``` java
        @CrossOrigin(origins = "*")
        @PostMapping("/addstudent")
        public Student addStudent(@RequestBody Student student) {
            // Implementation
        }
    ```
- Add Multiple Student
    - (Insert/Post) Insert Multiple student
    - Required: List of Student Object: List of Student
    - Response: 
        - Success: null
        - Fail: List of Student Object who cound not be inserted
     ``` java
        @CrossOrigin(origins = "*")
        @PostMapping("/addstudents")
        public List<Student> addStudents(@RequestBody List<Student> students) {
        // Implementation
        }
    ```
    
- Update Student Basic Student info
    - (Update/Put) Updates a student's basic information that does not affect other classes
    - Required: Student Object: Student
    - Response: 
        - Success: Student object
        - Fail: null
     ``` java
        @CrossOrigin(origins = "*")
        @PutMapping("/updatestudent")
        public Student updateStudent(@RequestBody Student student){
            // Implementation
        }
    ```
   
- Update Student Schedule
    - (Update/Get) Updates a Schedule. Switch one section for another in the same period
    - Required: 
        -  Student id: String
        -  Period: int
        -  ClassId to be changed: String
    - Response: 
        - Success: Student object
        - Fail: null
     ``` java
        @CrossOrigin(origins = "*")
        @GetMapping("/updatePeriod/{id}/{period}/{classId}")
        public Student updateStudentSchedule(@PathVariable String id,
         @PathVariable int period, @PathVariable String classId){
            // Implementation
        }
    ```

- Delete Student  
    - (Delete/Delete) Deletes a students
    - Required: Student Id: String
    - Response: NA
     ``` java
        @CrossOrigin(origins = "*")
        @DeleteMapping(path = "/deletestudentId/{id}")
        public String deleteS(@PathVariable String id){
        // Implementation
        }
    ```
### Teachers
- Get Teachers 
    - (Read/Get) Gets all of the teachers in the Database
    - Required: NA
    - Response: List of all teachers present
     ``` java
        @CrossOrigin(origins = "*")
        @GetMapping("/teachers")
        public List<Teacher> getTeachers() {
          return teacherRepo.findAll();
        }
    ```
- Find Teacher 
    - (Read/Get) String variable of teacher ID
    - Required: Student ID: String
    - Response: 
        - Success: Teacher
        - Fail: null
     ``` java
        @CrossOrigin(origins = "*")
        @GetMapping("/findteacher/{id}")
        public Teacher findTeacher(@PathVariable String id) {
          //implementation
        }
    ```

- Add Teacher  
    - (Insert/Post) Insert a teacher
    - Required: Teacher Object: Teacher
    - Response: 
        - Success: null
        - Fail: Teacher Object not inserted
     ``` java
        @CrossOrigin(origins = "*")
        @PostMapping("/addteacher")
        public Teacher addTeacher(@RequestBody Teacher teacher) {

            // Implementation
        }
    ```
- Add Multiple Teachers
    - (Insert/Post) Insert Multiple teachers
    - Required: List of Teacher Object: List of Teachers
    - Response: 
        - Success: null
        - Fail: List of Teacher Object not inserted
     ``` java
        @CrossOrigin(origins = "*")
        @PostMapping("/addteachers")
        public List<Teacher> addTeachers(@RequestBody List<Teacher> teachers) {
        // Implementation
        }
    ```
    
- Update Teacher Basic Student info
    - (Update/Put) Updates a teacher's basic information that does not affect other classes
    - Required: Teacher Object: Teacher
    - Response: 
        - Success: Teacher object
        - Fail: null
     ``` java
        @CrossOrigin(origins = "*")
        @PutMapping("/updateteacher")
        public Teacher updateTeacher(@RequestBody Teacher teacher){
            // Implementation
        }
    ```
- Delete Teacher  
    - (Delete/Delete) Deletes a Teacher
    - Required: Teacher Id: String
    - Response: NA
     ``` java
        @CrossOrigin(origins = "*")
        @DeleteMapping(path = "/deleteteacherId/{id}")
         public String deleteTeacher(@PathVariable String id){
        // Implementation
        }
    ```    
### Classes
- Get Classes 
    - (Read/Get) Gets all of the Classes in the Database
    - Required: NA
    - Response: List of all classes present
     ``` java
        @CrossOrigin(origins = "*")
        @GetMapping("/classes")
        public List<Class> getClasses()
          //implementation
        }
    ```
- Find Class 
    - (Read/Get) String variable of class ID
    - Required: class ID: String
    - Response: 
        - Success: Teacher
        - Fail: null
     ``` java
        @CrossOrigin(origins = "*")
        @GetMapping("/findclass/{id}")
        public Class findClass(@PathVariable String id) {
          //implementation
        }
    ```

- Add Class  
    - (Insert/Post) Insert a class
    - Required: Class Object: Class
    - Response: 
        - Success: null
        - Fail: Teacher Object not inserted
     ``` java
        @CrossOrigin(origins = "*")
        @PostMapping("/addclass")
        public Class addClass(@RequestBody Class classs) {
            // Implementation
        }
    ```
- Add Multiple Teachers
    - (Insert/Post) Insert Multiple teachers
    - Required: List of Teacher Object: List of Teachers
    - Response: 
        - Success: null
        - Fail: List of Class Object not inserted
     ``` java
        @CrossOrigin(origins = "*")
        @PostMapping("/addclasses")
        public List<Class> addClasses(@RequestBody List<Class> classes) {
        // Implementation
        }
    ```
    
- Update Class Basic Class info
    - (Update/Put) Updates a class's basic information that does not affect other classes
    - Required: Class Object: Class
    - Response: 
        - Success: Class object
        - Fail: null
     ``` java
        @CrossOrigin(origins = "*")
        @PutMapping("/updateclass")
        public Class updateClass(@RequestBody Class c){
            // Implementation
        }
    ```
- Delete Class  
    - (Delete/Delete) Deletes a Class
    - Required: Class Id: String
    - Response: NA
     ``` java
        @CrossOrigin(origins = "*")
        @DeleteMapping("/deleteclassId/{id}")
        public void deleteClass(@PathVariable String id){
        // Implementation
        }
 

### Sections
### Other
- Reset Database
    - (Delete/Get) Wipes out the Database and re-seeds the Database. Display purposesd
    - Required: NA
    - Response: NA
     ``` java
        @CrossOrigin(origins = "*")
        @GetMapping("/reset")
        public void reset(){
            classSeeder.seed();
            sectionSeeder.seed();
            studentSeeder.seed();
            teacherSeeder.seed();
        }
    ```
