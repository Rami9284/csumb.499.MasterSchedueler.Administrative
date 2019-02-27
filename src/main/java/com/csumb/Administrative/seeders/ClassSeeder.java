package com.csumb.Administrative.seeders;

import com.csumb.Administrative.entities.Class;
import com.csumb.Administrative.repositotries.IClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ClassSeeder implements CommandLineRunner {
    @Autowired
    private IClassRepository classRepository;

    @Override
    public void run(String... args)throws  Exception{
        Class c1 = new Class("English", "Literature", "400");
       
        Class c2 = new Class( "Math", "Geometry", "101");
        Class c3 = new Class("Art", "Digital Art","201");
        Class c4 = new Class("Science", "Chemistry", "301");
        Class c5 = new Class("English","AP English", "401");
        Class c6 = new Class("History","Current Evevnts", "501");
        Class c7 = new Class("Math", "Algebra 1", "102");
        Class c8 = new Class("History", "World History", "502");
        Class c9 = new Class("English", "English1A", "402");
        Class c10 = new Class("Art", "Graphic Design", "202");
        Class c11 = new Class("Science", "Biology", "302");
        Class c12 = new Class("Social studies","World History Fast");
                

        classRepository.deleteAll();
        List<Class> classes = Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12);
        classRepository.saveAll(classes);
    }

}