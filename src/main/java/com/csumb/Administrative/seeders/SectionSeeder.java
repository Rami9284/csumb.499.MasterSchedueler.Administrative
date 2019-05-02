package com.csumb.Administrative.seeders;

import com.csumb.Administrative.entities.Section;
import com.csumb.Administrative.repositotries.ISectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.mapping.Document;
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
        seed();

    }

    public void seed(){
        Class none = new Class("NONE","NO CLASS","10101",6,1000);
       List<Section> sections = new ArrayList<>();
       for(int i =1; i<= 6;i++){
           sections.add(new Section(none,i,i));
       }

        sectionRepository.deleteAll();
        sectionRepository.saveAll(sections);

    }
}