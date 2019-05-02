package com.csumb.Administrative.seeders;

import com.csumb.Administrative.entities.Class;
import com.csumb.Administrative.repositotries.IClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ClassSeeder implements CommandLineRunner {
    @Autowired
    private IClassRepository classRepository;

    @Override
    public void run(String... args)throws  Exception{
        seed();

    }

    public void seed(){
        List<Class> classes = Arrays.asList(
                new Class("MATH","ALGEBRA","12314"),
                new Class("MATH","ADVANCED ALGEBRA","qweq1"),
                new Class("MATH","HIGH SCHOOL MATHEMATICS I","fsd1"),
                new Class("MATH","HIGH SCHOOL MATHEMATICS II","ar4qafdgw"),

                new Class("ENGLISH","ENGLISH 1-2","asd3"),
                new Class("ENGLISH","ENGLISH 3-4","hfgb "),
                new Class("ENGLISH","ENGLISH 5-6","jhf"),
                new Class("ENGLISH","ENGLISH 7-8","iyhgd"),

                new Class("SCIENCE","INTRODUCTION TO LIFE SCIENCE","iohds"),
                new Class("SCIENCE","BIOLOGY","ponvd "),
                new Class("SCIENCE","MARINE BIOLOGY","oinv"),
                new Class("SCIENCE","CHEMISTRY","5y4d"),

                new Class("LANGUAGE","SPANISH 1","sfaefr"),
                new Class("LANGUAGE","SPANISH 2","fadf44 "),
                new Class("LANGUAGE","FRENCH 1","gfh5y"),
                new Class("LANGUAGE","FRENCH 2","65gfv"),

                new Class("ARTS","MUSIC 1","asd24",5,30),
                new Class("ARTS","MUSIC 2","fagyjudf44 ",5,30),
                new Class("ARTS","ART 1","yjthb",5,30),
                new Class("ARTS","ART 2","65gasffv",5,30),
                new Class("ARTS","DIGITAL ART","sfaef4ysdr",5,30),
                new Class("ARTS","WOOD SHOP","faasoins44 ",5,30),
                new Class("ARTS","CULINARY","asdasf",5,30),
                new Class("ARTS","CERAMICS","asfvdfb",5,30),

                new Class("NONE","NO CLASS","10101",6,1000)

                );



        classRepository.deleteAll();
        classRepository.saveAll(classes);
    }

}