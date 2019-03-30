package com.csumb.Administrative;

import com.csumb.Administrative.entities.Student;
import com.csumb.Administrative.entities.Teacher;
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
public class AdministrativeControllerTest {
    @Autowired
    private AdministrativeController administrativeController;

    @MockBean
    private IStudentRepository studentRepository;

    @MockBean
    private ITeacherRepository teacherRepository;

    private List<Student> studentData = new ArrayList<>(Arrays.asList(new Student("123", "Edith",9),
            new Student("345", "Daniel", 10)));

    private List<Teacher> teacherData = new ArrayList<>(Arrays.asList(new Teacher("098","Ms. Gonzalez","English"),
            new Teacher("876", "Ms. Gurcha", "Science")));

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
        Assert.assertEquals(null,administrativeController.addTeachers(teacherData));

        //when an error occurs
        when(teacherRepository.insert(teacherData.get(0))).thenThrow(new DuplicateKeyException("key", new Throwable()));
        when(teacherRepository.insert(teacherData.get(1))).thenThrow(new DuplicateKeyException("key", new Throwable()));
        Assert.assertEquals(teacherData, administrativeController.addTeachers(teacherData));
    }

}
