package com.csumb.Administrative.seeders;

import com.csumb.Administrative.entities.Teacher;
import com.csumb.Administrative.repositotries.ITeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class TeacherSeeder  implements CommandLineRunner {
    @Autowired
    private ITeacherRepository teacherRepository;

    @Override
    public void run(String... args)throws  Exception {
        Teacher t1 = new Teacher("0998","Ms. Gonzalez","Social Studies","World History" );
//        Teacher t2 = new Teacher("876", "Ms. Gurcha", "Science", "Biology");
//        Teacher t3 = new Teacher("656", "Ms. Ramirez", "Art", "Digital Art");
//        Teacher t4 = new Teacher("216", "Mr. Aguila", "Math","Algebra");
//        Teacher t5 = new Teacher("356", "Mr. Ferguson", "Science","Physics");
//        Teacher t6 = new Teacher("615", "Mr. O\'Brien", "English","Forsh English");
//        Teacher t7 = new Teacher("522", "Mr. kent", "Math","Algebra");
//        Teacher t8 = new Teacher("760", "Ms. Andrews", "English", "Frosh English AP");
//        Teacher t9 = new Teacher("627", "Mr. Robertson", "History","World History");
//        Teacher t10 = new Teacher("348", "Ms. Johnston", "Science","Biology");
//

        teacherRepository.deleteAll();
        List<Teacher> teachers = Arrays.asList(t1);//,t2,t3,t4,t5,t6,t7,t8,t9,t10);
        teacherRepository.saveAll(teachers);
    }
}
