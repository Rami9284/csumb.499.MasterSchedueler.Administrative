package com.csumb.Administrative;

import com.csumb.Administrative.entities.Section;
import com.csumb.Administrative.entities.Student;
import com.csumb.Administrative.entities.Teacher;
import com.csumb.Administrative.entities.Class;
import com.csumb.Administrative.repositotries.ISectionRepository;
import com.csumb.Administrative.repositotries.IStudentRepository;
import com.csumb.Administrative.repositotries.ITeacherRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdministrativeControllerTest {
    @Autowired
    private AdministrativeController administrativeController;

    @MockBean
    private IStudentRepository studentRepository;

    @MockBean ISectionRepository sectionRepository;

    @MockBean
    private ITeacherRepository teacherRepository;

    private List<Student> studentData = new ArrayList<>(Arrays.asList(
            new Student("123", "Edith",9),
            new Student("345", "Daniel", 10)));

    private List<Teacher> teacherData = new ArrayList<>(Arrays.asList(new Teacher("098","Ms. Gonzalez","English"),
            new Teacher("876", "Ms. Gurcha", "Science")));

    private List<Teacher> sectionData = new ArrayList<>(Arrays.asList(new Teacher("098","Ms. Gonzalez","English"),
    new Teacher("876", "Ms. Gurcha", "Science")));

    //all Students
    @Test
    public void getStudents(){
        when(studentRepository.findAll()).thenReturn(studentData);
        Assert.assertEquals(studentData, administrativeController.getStudents());
    }

    @Test
    public void findStudent(){
        Student s = new Student("123", "Daniel",9);
        when(studentRepository.findById("123")).thenReturn(Optional.of(s));
        Assert.assertEquals(s, administrativeController.findStudent("123"));
    }

    @Test
    public void noStudentFound(){
        when(studentRepository.findById("not a real id")).thenReturn(Optional.empty());
        Assert.assertNull(administrativeController.findStudent("not a real id"));
    }

    @Test
    public void addStudents(){
        //When there is not an error
        when(studentRepository.insert(studentData.get(0))).thenReturn(studentData.get(0));
        when(studentRepository.insert(studentData.get(1))).thenReturn(studentData.get(1));
        Assert.assertEquals(null,administrativeController.addStudents(studentData));

        //when an error occurs
        when(studentRepository.insert(studentData.get(0))).thenThrow(new DuplicateKeyException("key", new Throwable()));
        when(studentRepository.insert(studentData.get(1))).thenThrow(new DuplicateKeyException("key", new Throwable()));
        Assert.assertEquals(studentData, administrativeController.addStudents(studentData));
    }

    @Test
    public void addStudent(){
        Student s = new Student("123", "Daniel",9);
        when(studentRepository.insert(s)).thenReturn(s);
        Assert.assertNull(administrativeController.addStudent(s));
    }

    @Test
    public void updateStudent() {
        Student s = new Student("123", "Daniel", 9);
        when(studentRepository.findById(s.getId())).thenReturn(Optional.of(s));
        Student expected = new Student("123", "Daniel", 10);
        when(studentRepository.save(expected)).thenReturn(expected);
        Assert.assertEquals(expected, administrativeController.updateStudent(expected));
    }

    @Test
    public void upDateStudentNotFount(){
        when(studentRepository.findById("No Id")).thenReturn(Optional.empty());
        Student expected = new Student("No Id","Daniel",10);
        Assert.assertNull(administrativeController.updateStudent(expected));
    }

    @Test
    public void updateStudentSchedule(){
        List<String> schedule = new ArrayList<>(Arrays.asList("Bio","Algebra","PE","Wood Shop","Art","French"));
        List<String> scheduleId = new ArrayList<>(Arrays.asList("1_2","2_3","3_4","4_5","5_6","6_1"));
        Student s = new Student("123", "Daniel", 9,schedule, scheduleId);
        when(studentRepository.findById("123")).thenReturn(Optional.of(s));
        Class c = new Class("Science", "Bio","1");
        Section sec1 = new Section(c,2);
        Class c2 = new Class("Science", "Marine Bio","8");
        Section sec2 = new Section(c2,6);
        when(sectionRepository.findById("1_2")).thenReturn(Optional.of(sec1));
        when(sectionRepository.findById("8_6")).thenReturn(Optional.of(sec2));
        List<String> sc = new ArrayList<>(Arrays.asList("Marine Bio","Algebra","PE","Wood Shop","Art","French"));
        List<String> scId = new ArrayList<>(Arrays.asList("8_6","2_3","3_4","4_5","5_6","6_1"));
        Student expected = new Student("123", "Daniel", 9,sc, scId);
        when(studentRepository.save(expected)).thenReturn(expected);
        Assert.assertEquals(expected,administrativeController.updateStudentSchedule("123",1,"8_6"));
    }

    @Test
    public void deleteStudent(){
        Assert.assertNull(administrativeController.deleteS("123"));
    }


    //all Teachers
    @Test
    public void getAllTeachers(){
        when(teacherRepository.findAll()).thenReturn(teacherData);
        Assert.assertEquals(teacherData, administrativeController.getTeachers());
    }


    @Test
    public void findTeacher(){
        Teacher t = new Teacher("124","Gonsalez", "MATH");
        when(teacherRepository.findById("124")).thenReturn(Optional.of(t));

        Assert.assertEquals(t,administrativeController.findTeacher("124"));
    }

    @Test
    public void notFoundTeacher(){
        when(teacherRepository.findById("124")).thenReturn(Optional.empty());
        Assert.assertNull(administrativeController.findTeacher("124"));
    }

    @Test
    public void addTeacher(){
        //When there is not an error
        when(teacherRepository.insert(teacherData.get(0))).thenReturn(teacherData.get(0));
        when(teacherRepository.insert(teacherData.get(1))).thenReturn(teacherData.get(1));
        Assert.assertEquals(null,administrativeController.addTeachers(teacherData));

        //when an error occurs
        when(teacherRepository.insert(teacherData.get(0))).thenThrow(new DuplicateKeyException("key", new Throwable()));
        when(teacherRepository.insert(teacherData.get(1))).thenThrow(new DuplicateKeyException("key", new Throwable()));
        Assert.assertEquals(teacherData, administrativeController.addTeachers(teacherData));
    }

}
