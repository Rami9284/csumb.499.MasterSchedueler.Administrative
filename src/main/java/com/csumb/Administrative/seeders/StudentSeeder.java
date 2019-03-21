package com.csumb.Administrative.seeders;

import com.csumb.Administrative.entities.Student;
import com.csumb.Administrative.entities.Class;
import com.csumb.Administrative.repositotries.IStudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StudentSeeder implements CommandLineRunner {
    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public void run(String... args)throws  Exception{
        List<Student> fastStudents = new ArrayList<>();

        
        for( int i = 0; i < 30; i++){

            Student s = new Student("1234" +i,"student_" + i, 10, "fast");
            s.setPreferred_classes(Arrays.asList(Pair.of(new Class(i + "098","social studies","world history fast"), true)));
            fastStudents.add(s);
        }

        List<Student> students = Arrays.asList(
            new Student("123", "Edith",9),
            new Student("345", "Daniel", 10),
            new Student("567", "Judith", 11),
            new Student("789", "Manjit", 12),
            new Student("638", "Betty",9),
            new Student("577", "Alexia", 10),
            new Student("374", "Ciaran", 11),
            new Student("348", "Lola", 12),
            new Student("139", "Yasin",9),
            new Student("657", "Gerald", 10),
            new Student("816", "Rocco", 11),
            new Student("856", "Glenn", 12),
            new Student("246", "Samantha",9),
            new Student("378", "Fleur", 10),
            new Student("896", "May", 11),
            new Student("741", "George", 12),
            new Student("586", "Jesse",9),
            new Student("835", "Melvin", 10),
            new Student("518", "Jonah", 11),
            new Student("249", "Aron", 12),
            new Student("173", "Rhea",9),
            new Student("996", "Sofia", 10),
            new Student("321", "Aimee", 11),
            new Student("576", "Maria", 12),
            new Student("313", "Jeremy",9),
            new Student("479", "Nicholas", 10),
            new Student("717", "Andre", 11),
            new Student("959", "Joshua", 12),
            new Student("515", "Hafsah",9),
            new Student("562", "Stacy", 10),
            new Student("570", "Nellie", 11),
            new Student("228", "Niamh", 12),
            new Student("289", "Harry",9),
            new Student("680", "William", 10),
            new Student("488", "Tommy", 11),
            new Student("265", "Rafael", 12)
        );

        studentRepository.deleteAll();
        studentRepository.saveAll(students);
        studentRepository.saveAll(fastStudents);
    }
}
