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
        Teacher t1 = new Teacher("098","Ms. Gonzalez","English");
        Teacher t2 = new Teacher("876", "Ms. Gurcha", "Science");
        Teacher t3 = new Teacher("656", "Ms. Ramirez", "Art");
        Teacher t4 = new Teacher("216", "Mr. Aguila", "Math");
        Teacher t5 = new Teacher("356", "Mr. Ferguson", "Science");
        Teacher t6 = new Teacher("615", "Mr. O\'Brien", "English");
        Teacher t7 = new Teacher("522", "Mr. kent", "Math");
        Teacher t8 = new Teacher("760", "Ms. Andrews", "English");
        Teacher t9 = new Teacher("627", "Mr. Robertson", "History");
        Teacher t10 = new Teacher("348", "Ms. Johnston", "Science");
        // Teacher t11 = new Teacher("435", "Ms. Chambers", "Art", 2);
        // Teacher t12 = new Teacher("488", "Ms. Marquez", "History", 1);
        // Teacher t13 = new Teacher("767", "Mr. Freeman", "English", 4);
        // Teacher t14 = new Teacher("211", "Ms. Stewart", "Art", 5);
        // Teacher t15 = new Teacher("384", "Ms. Hamilton", "History", 2);
        // Teacher t16 = new Teacher("930", "Mr. Flowers", "History", 6);
        // Teacher t17 = new Teacher("680", "Mr. Moore", "Art", 4);
        // Teacher t18 = new Teacher("110", "Ms. Lowe", "English", 3);
        // Teacher t19 = new Teacher("667", "Ms. Doyle", "Math", 2);
        // Teacher t20 = new Teacher("272", "Mr. Reid", "Science", 1);
        

        teacherRepository.deleteAll();
        List<Teacher> teachers = Arrays.asList(t1,t2,t3,t4,t5,t6,t7,t8,t9,t10);
        teacherRepository.saveAll(teachers);
    }
}
