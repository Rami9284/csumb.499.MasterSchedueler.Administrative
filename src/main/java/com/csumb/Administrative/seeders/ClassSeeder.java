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
                new Class("Math","Algebra","432","arg34qaw"),
                new Class("Social Studies","World History","201","4875t29"),
                new Class("Science","Biology","422","765sdf2"),
                new Class("Language","French","332","875sd4"),
                new Class("Science", "Health", "470","1212dx3"),
                new Class("Vocational", "Culinary", "101","32sdz1"),
                new Class("Art", "Digital Art","201","435"),
                new Class("Science", "Chemistry", "301","867"));
//                new Class("Science", "Chemistry", "301","654"),
//                new Class("English","AP English", "401","876"),
//                new Class("History","Current Events", "501","6752"),
//                new Class("English", "English1A", "402","5432"));



        classRepository.deleteAll();
        classRepository.saveAll(classes);
    }

}