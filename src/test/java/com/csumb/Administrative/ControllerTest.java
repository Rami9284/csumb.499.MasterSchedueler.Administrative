package com.csumb.Administrative;

import com.csumb.Administrative.entities.Class;
import com.csumb.Administrative.entities.Student;
import com.csumb.Administrative.entities.Teacher;
import com.csumb.Administrative.repositotries.IClassRepository;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {
    @Autowired
    private AdministrativeController administrativeController;

    @MockBean
    private IStudentRepository studentRepository;

    @MockBean
    private ITeacherRepository teacherRepository;

    @MockBean
    private IClassRepository classRepository;

    private List<Student> studentData = new ArrayList<>(Arrays.asList(new Student("123", "Edith",9),
            new Student("345", "Daniel", 10)));

    private List<Teacher> teacherData = new ArrayList<>(Arrays.asList(new Teacher("098","Ms. Gonzalez","English", 1),
            new Teacher("876", "Ms. Gurcha", "Science", 4)));

    private List<Class> classData = new ArrayList<>(Arrays.asList(new Class("430","Math", "Algebra"), new Class("989","Math", "Calc")));

    //all Students
    @Test
    public void getAllStudents(){
        when(studentRepository.findAll()).thenReturn(studentData);
        Assert.assertEquals(studentData, administrativeController.getStudents());
    }

    //insert students with out an error
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
    public void updateStudents(){
        when(studentRepository.saveAll(studentData)).thenReturn(studentData);
        Assert.assertEquals(studentData, administrativeController.updateStudents(studentData));
    }

    //all Teachers
    @Test
    public void getAllTeachers(){
        when(teacherRepository.findAll()).thenReturn(teacherData);
        Assert.assertEquals(teacherData, administrativeController.getTeachers());
    }

    @Test
    public void addTeacher(){
        //When there is not an error
        when(teacherRepository.insert(teacherData.get(0))).thenReturn(teacherData.get(0));
        when(teacherRepository.insert(teacherData.get(1))).thenReturn(teacherData.get(1));
        Assert.assertEquals(null,administrativeController.addTeacher(teacherData));

        //when an error occurs
        when(teacherRepository.insert(teacherData.get(0))).thenThrow(new DuplicateKeyException("key", new Throwable()));
        when(teacherRepository.insert(teacherData.get(1))).thenThrow(new DuplicateKeyException("key", new Throwable()));
        Assert.assertEquals(teacherData, administrativeController.addTeacher(teacherData));
    }

    @Test
    public void updateTeachers(){
        when(teacherRepository.saveAll(teacherData)).thenReturn(teacherData);
        Assert.assertEquals(teacherData, administrativeController.updateTeachers(teacherData));
    }

    //all Classes
    @Test
    public void getAllClasses(){
        when(classRepository.findAll()).thenReturn(classData);
        Assert.assertEquals(classData, administrativeController.getClasses());
    }

    @Test
    public void addClasses(){
        //When there is not an error
        when(classRepository.insert(classData.get(0))).thenReturn(classData.get(0));
        when(classRepository.insert(classData.get(1))).thenReturn(classData.get(1));
        Assert.assertEquals(null,administrativeController.addClasses(classData));

        //when an error occurs
        when(classRepository.insert(classData.get(0))).thenThrow(new DuplicateKeyException("key", new Throwable()));
        when(classRepository.insert(classData.get(1))).thenThrow(new DuplicateKeyException("key", new Throwable()));
        Assert.assertEquals(classData, administrativeController.addClasses(classData));
    }

    @Test
    public void updateClasses(){
        when(classRepository.saveAll(classData)).thenReturn(classData);
        Assert.assertEquals(classData, administrativeController.updateClasses(classData));
    }

}
