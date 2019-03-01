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
        Student s1 = new Student("123", "Edith",9);
        Student s2 = new Student("345", "Daniel", 10);
        Student s3 = new Student("567", "Judith", 11);
        Student s4 = new Student("789", "Manjit", 12);

        Student s5 = new Student("638", "Betty",9);
        Student s6 = new Student("577", "Alexia", 10);
        Student s7 = new Student("374", "Ciaran", 11);
        Student s8 = new Student("348", "Lola", 12);

        Student s9 = new Student("139", "Yasin",9);
        Student s10 = new Student("657", "Gerald", 10);
        Student s11 = new Student("816", "Rocco", 11);
        Student s12 = new Student("856", "Glenn", 12);

        Student s13 = new Student("246", "Samantha",9);
        Student s14 = new Student("378", "Fleur", 10);
        Student s15 = new Student("896", "May", 11);
        Student s16 = new Student("741", "George", 12);

        Student s17 = new Student("586", "Jesse",9);
        Student s18 = new Student("835", "Melvin", 10);
        Student s19 = new Student("518", "Jonah", 11);
        Student s20 = new Student("249", "Aron", 12);

        Student s21 = new Student("173", "Rhea",9);
        Student s22 = new Student("996", "Sofia", 10);
        Student s23 = new Student("321", "Aimee", 11);
        Student s24 = new Student("576", "Maria", 12);

        Student s25 = new Student("313", "Jeremy",9);
        Student s26 = new Student("479", "Nicholas", 10);
        Student s27 = new Student("717", "Andre", 11);
        Student s28 = new Student("959", "Joshua", 12);

        Student s29 = new Student("515", "Hafsah",9);
        Student s30 = new Student("562", "Stacy", 10);
        Student s31 = new Student("570", "Nellie", 11);
        Student s32 = new Student("228", "Niamh", 12);

        Student s33 = new Student("289", "Harry",9);
        Student s34 = new Student("680", "William", 10);
        Student s35 = new Student("488", "Tommy", 11);
        Student s36 = new Student("265", "Rafael", 12);

        List<Student> fastStudents = new ArrayList<>();
        for( int i = 0; i < 30; i++){

            Student s = new Student("1234" +i,"student_" + i, 10);
            s.setPreferred_classes(Arrays.asList(Pair.of(new Class("social studies","world history fast"), true)));
            fastStudents.add(s);
        }



        studentRepository.deleteAll();
        List<Student> students = Arrays.asList(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,
                                            s11,s12,s13,s14,s15,s16,s17,s18,s19,
                                            s20,s21,s22,s23,s24,s25,s26,s27,s28,s29,
                                            s30,s31,s32,s33,s34,s35,s36);
        studentRepository.saveAll(students);
        studentRepository.saveAll(fastStudents);
    }
}
