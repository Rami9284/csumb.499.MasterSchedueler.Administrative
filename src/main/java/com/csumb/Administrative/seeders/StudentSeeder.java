package com.csumb.Administrative.seeders;

import com.csumb.Administrative.entities.Student;
import com.csumb.Administrative.entities.Class;
import com.csumb.Administrative.repositotries.IStudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StudentSeeder implements CommandLineRunner {
    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public void run(String... args)throws  Exception{
        seed();

    }
    public void seed(){
        List<Student> students = new ArrayList<>();
        for( int i = 0; i < 150; i++){
            Student s = new Student("1234" +i,"student_" + i, 10, "none");
            s.setPreferredClasses(Arrays.asList("World History","Algebra","Biology","French",
                    "Health","Culinary","Digital Art", "Chemistry"));
            s.setPreferred(Arrays.asList(false,false,false,false,false,false,false,false));
            students.add(s);
        }

        studentRepository.deleteAll();
        studentRepository.saveAll(students);
    }
}
