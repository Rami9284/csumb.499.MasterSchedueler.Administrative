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

        Section s1 = new Section(new Class("English", "Literature", "400"),1);
        Section s2 = new Section(new Class("English", "Literature", "400"),2);
        Section s3 = new Section(new Class("Math", "Geometry", "101"),3);
        Section s4 = new Section(new Class("Math", "Geometry", "101"),4);
        Section s5 = new Section(new Class("Art", "Digital Art","201"),5);
        Section s6 = new Section(new Class("Art", "Digital Art","201"),6);
        Section s7 = new Section(new Class("Science", "Chemistry", "301"),7);

        Section s8 = new Section(new Class("Science", "Chemistry", "301"),8);
        Section s9 = new Section(new Class("English","AP English", "401"),9);
        Section s10 = new Section(new Class("History","Current Events", "501"),10);
        Section s11 = new Section(new Class("History","Current Events", "501"),11);
        Section s12 = new Section(new Class("English", "English1A", "402"),12);
        Section s13 = new Section(new Class("English", "English1A", "402"),13);

        sectionRepository.deleteAll();
        List<Section> sections = Arrays.asList(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13);
        sectionRepository.saveAll(sections);
    }
}
