package com.csumb.Administrative.seeders;

import com.csumb.Administrative.entities.Teacher;
import com.csumb.Administrative.repositotries.ITeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class TeacherSeeder implements CommandLineRunner {
    @Autowired
    private ITeacherRepository teacherRepository;

    @Override
    public void run(String... args)throws  Exception {

        List<Teacher> teachers = Arrays.asList(
            new Teacher("098","Ms. Gonzalez","English","Forsh English" ),
            new Teacher("876", "Ms. Gurcha", "Science", "Biology"),
            new Teacher("656", "Ms. Ramirez", "Art", "Digital Art"),
            new Teacher("216", "Mr. Aguila", "Math","Algebra"),
            new Teacher("356", "Mr. Ferguson", "Science","Physics"),
            new Teacher("615", "Mr. O\'Brien", "English","Forsh English"),
            new Teacher("522", "Mr. kent", "Math","Algebra"),
            new Teacher("760", "Ms. Andrews", "English", "Frosh English AP"),
            new Teacher("627", "Mr. Robertson", "History","World History"),
            new Teacher("348", "Ms. Johnston", "Science","Biology")
            
        );

        teacherRepository.deleteAll();
        teacherRepository.saveAll(teachers);
    }
}
