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

        Section s1 = new Section(new Class("0012", "English", "Literature", "400", "760"),1);
        Section s2 = new Section(new Class("0012", "English", "Literature", "400", "760"),2);
        Section s3 = new Section(new Class("0000", "Math", "Geometry", "101", "216"),3);
        Section s4 = new Section(new Class("0000", "Math", "Geometry", "101", "216"),4);
        Section s5 = new Section(new Class("0101", "Art", "Digital Art","201", "656"),5);
        Section s6 = new Section(new Class("0101", "Art", "Digital Art","201", "656"),6);
        Section s7 = new Section(new Class("1234" , "Science", "Chemistry", "301", "356"),7);

        Section s8 = new Section(new Class("1234" , "Science", "Chemistry", "301", "356"),8);
        Section s9 = new Section(new Class("3456", "English","AP English", "401", "760"),9);
        Section s10 = new Section(new Class("2345", "History","Current Evevnts", "501", "627"),10);
        Section s11 = new Section(new Class("2345", "History","Current Evevnts", "501", "627"),11);
        Section s12 = new Section(new Class("6789", "English", "English1A", "402", "615"),12);
        Section s13 = new Section(new Class("6789", "English", "English1A", "402", "615"),13);

       
        sectionRepository.deleteAll();
        List<Section> sections = Arrays.asList(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13);
        sectionRepository.saveAll(sections);
    }
}
