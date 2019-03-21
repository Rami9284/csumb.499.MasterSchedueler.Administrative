package com.csumb.Administrative.seeders;

import com.csumb.Administrative.entities.Section;
import com.csumb.Administrative.repositotries.ISectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.csumb.Administrative.entities.Class;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SectionSeeder implements CommandLineRunner {
    @Autowired
    private ISectionRepository sectionRepository;

    @Override
    public void run(String... args)throws  Exception{

        List<Class> classes = Arrays.asList(
            new Class("English", "Literature", "400","123"),
            new Class("Math", "Geometry", "101","321"),
            new Class("Art", "Digital Art","201","435"),
            new Class("Science", "Chemistry", "301","867"),
            new Class("Science", "Chemistry", "301","654"),
            new Class("English","AP English", "401","876"),
            new Class("History","Current Events", "501","6752"),
            new Class("English", "English1A", "402","5432")
        );

       List<Section> sections = new ArrayList<>();
       for(Class c : classes){
           sections.add(new Section(c,1));
           sections.add(new Section(c,2));
       }
        sectionRepository.deleteAll();
        sectionRepository.saveAll(sections);
    }
}
