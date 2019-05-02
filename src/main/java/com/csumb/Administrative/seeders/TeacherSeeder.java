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
        seed();
    }

    public void seed(){
        List<Teacher> teachers = Arrays.asList(
                new Teacher("0998","Ms. Gonzalez","MATH",
                "ALGEBRA" ,"ADVANCED ALGEBRA", "HIGH SCHOOL MATHEMATICS I", 5),
                new Teacher("13qwsd","Ms. Z","MATH",
                        "ADVANCED ALGEBRA" ,"ALGEBRA", "HIGH SCHOOL MATHEMATICS I", 5),
                new Teacher("dgasd","Ms. Ramos","MATH",
                        "HIGH SCHOOL MATHEMATICS II" ,"ALGEBRA", "HIGH SCHOOL MATHEMATICS I", 5),
                new Teacher("sfat3","Ms. Hersh","MATH",
                        "HIGH SCHOOL MATHEMATICS II" ,"ALGEBRA", "HIGH SCHOOL MATHEMATICS I", 5),

                new Teacher("lkfnb","Ms. Patric","ENGLISH",
                        "ENGLISH 1-2" ,"ENGLISH 3-4", "ENGLISH 5-6", 5),
                new Teacher("asf23","Ms. Sponge","ENGLISH",
                        "ENGLISH 3-4" ,"ENGLISH 7-8", "ENGLISH 5-6", 5),
                new Teacher("bgdd","Ms. Karma","ENGLISH",
                        "ENGLISH 1-2" ,"ENGLISH 3-4", "ENGLISH 5-6", 5),
                new Teacher("asf135f","Ms. Jihn","ENGLISH",
                        "ENGLISH 7-8" ,"ENGLISH 3-4", "ENGLISH 5-6", 5),

                new Teacher("asd13rg","Ms. Sona","SCIENCE",
                        "INTRODUCTION TO LIFE SCIENCE" ,"BIOLOGY", "MARINE BIOLOGY", 5),
                new Teacher("dgvb","Ms. Lulu","SCIENCE",
                        "MARINE BIOLOGY" ,"INTRODUCTION TO LIFE SCIENCE", "CHEMISTRY", 5),
                new Teacher("jnnb ","Ms. Leona","SCIENCE",
                        "BIOLOGY" ,"MARINE BIOLOGY", "INTRODUCTION TO LIFE SCIENCE", 5),
                new Teacher("dsa64","Ms. Jay","SCIENCE",
                        "BIOLOGY" ,"CHEMISTRY", "BIOLOGY", 5),

                new Teacher("gnhmc","Ms. Kermit","LANGUAGE",
                        "SPANISH 1" ,"SPANISH 2", "", 5),
                new Teacher("fshy4","Ms. arre","LANGUAGE",
                        "FRENCH 1" ,"FRENCH 2", "", 5),
                new Teacher("ascasrtgh","Ms. kerk","LANGUAGE",
                        "SPANISH 1" ,"SPANISH 2", "", 5),
                new Teacher("fda","Ms. Arellano","LANGUAGE",
                        "FRENCH 1" ,"FRENCH 2", "", 5),

                new Teacher("123","Ms. cisum","ARTS",
                        "MUSIC 1" ,"MUSIC 1", "", 5),
                new Teacher("aefth","Ms. Fairview","ARTS",
                        "ART 1" ,"ART 2", "", 5),
                new Teacher("htgfv","Ms. Liao","ARTS",
                        "DIGITAL ART" ,"", "", 5),
                new Teacher("asv ","Ms. Aguila","ARTS",
                        "WOOD SHOP" ,"", "", 5),
                new Teacher("fvgnewr","Ms. Maven","ARTS",
                        "CULINARY" ,"", "", 5),
                new Teacher("ghyjghfd","Ms. Spring","ARTS",
                        "CERAMICS" ,"", "", 5)
                );


        teacherRepository.deleteAll();
        teacherRepository.saveAll(teachers);
    }
}
