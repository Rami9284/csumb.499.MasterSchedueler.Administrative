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
       
        // Class c2 = new Class("0000", "Math", "Geometry", "101", "216");
        // Class c3 = new Class("0101", "Art", "Digital Art","201", "656");
        // Class c4 = new Class("1234" , "Science", "Chemistry", "301", "356");
        // Class c5 = new Class("3456", "English","AP English", "401", "760");
        // Class c6 = new Class("2345", "History","Current Evevnts", "501", "627");
        // Class c7 = new Class("4567", "Math", "Algebra 1", "102", "522");
        // Class c8 = new Class("5678", "History", "World History", "502","627");
        // Class c9 = new Class("6789", "English", "English1A", "402", "615");
        // Class c10 = new Class("7890", "Art", "Graphic Design", "202", "656");
        // Class c11 = new Class("8901", "Science", "Biology", "302", "876");
                

        classRepository.deleteAll();
        List<Class> classes = Arrays.asList(c1);//,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11);
        //List<Class> classes = new ArrayList<>();
        classRepository.saveAll(classes);
    }

}