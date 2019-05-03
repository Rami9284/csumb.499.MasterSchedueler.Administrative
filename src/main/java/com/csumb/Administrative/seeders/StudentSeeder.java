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
import java.util.Random;

@Component
public class StudentSeeder implements CommandLineRunner {
    @Autowired
    private IStudentRepository studentRepository;
    private static final List<String> names = Arrays.asList("Bethel Hicks", "Kai Kays","Priscilla Dingus","Taisha Apgar",
            "Noreen Dubuque","Buford Linkous","Rosie Orange","Terrell Mattix","Dell Whelan","Benedict Godlewski",
            "Tonda Bewley","Rupert Denker","Caitlin Gravitt","Elbert Dishner","Mallie Burd","Michelle Plant","Elia Rollo",
            "Hermelinda Deen","Winter Bierce","Maia Wehling","Hallie Rapp","Annamae Helper","Erma Julia","Tiara Amar",
            "Janene Rexrode", "Cinderella Nadel", "Anh Colman", "Dominica Soares", "Rosemary Hagerman", "Don Swearengin",
            "Nereida Ayala", "Leandra Lerman", "Virgen Overman", "Callie Arter", "Rosette Holguin", "Delinda Pitkin",
            "Ardelia Crusoe", "Peggy Wyman", "Babara Goodell", "Tien Avila", "Debbra Stacks", "Elwood Liberto", "Clay Bouie",
            "Dorine Laffey", "Kecia Drain", "Sarai Rathburn", "Xavier Varian", "Dino Pigg", "Amee Saechao", "Blair Leech");

    @Override
    public void run(String... args)throws  Exception{
        seed();

    }
    public void seed(){
        studentRepository.deleteAll();
        List<Student> freshman = new ArrayList<>();
        List<String> required = new ArrayList<>(Arrays.asList("HIGH SCHOOL MATHEMATICS I",
                "ENGLISH 1-2","INTRODUCTION TO LIFE SCIENCE"));
        List<String> tempC;
        for( int i = 0; i < 150; i++){
            Student s = new Student("1234" +i,names.get(i%50), 9, "none");
            tempC = new ArrayList<>();
            tempC.addAll(required);
            tempC.addAll(getElective());
            s.setPreferredClasses(tempC);
            s.setPreferred(getPreferred());
            freshman.add(s);
        }
        studentRepository.saveAll(freshman);


        List<Student> sophmore = new ArrayList<>();
        List<String> requiredS = new ArrayList<>(Arrays.asList("HIGH SCHOOL MATHEMATICS II",
                "ENGLISH 3-4","BIOLOGY"));
        for( int i = 0; i < 100; i++){
            Student s = new Student("3234" +i,names.get(i%50), 10, "none");
            tempC = new ArrayList<>();
            tempC.addAll(requiredS);
            tempC.addAll(getElective());
            s.setPreferredClasses(tempC);
            s.setPreferred(getPreferred());
            sophmore.add(s);
        }
        studentRepository.saveAll(sophmore);


        List<Student> jr = new ArrayList<>();
        List<String> requiredj = new ArrayList<>(Arrays.asList("ALGEBRA",
                "ENGLISH 5-6","MARINE BIOLOGY"));
        for( int i = 0; i < 75; i++){
            Student s = new Student("5434" +i,names.get(i%50), 11, "none");
            tempC = new ArrayList<>();
            tempC.addAll(requiredj);
            tempC.addAll(getElective());
            s.setPreferredClasses(tempC);
            s.setPreferred(getPreferred());
            sophmore.add(s);
        }
        studentRepository.saveAll(jr);

        List<Student> senior = new ArrayList<>();
        List<String> requiredSe = new ArrayList<>(Arrays.asList("ADVANCED ALGEBRA",
                "ENGLISH 7-8","CHEMISTRY"));
        for( int i = 0; i < 75; i++){
            Student s = new Student("5434" +i,names.get(i%50), 11, "none");
            tempC = new ArrayList<>();
            tempC.addAll(requiredSe);
            tempC.addAll(getElective());
            s.setPreferredClasses(tempC);
            s.setPreferred(getPreferred());
            senior.add(s);
        }
        studentRepository.saveAll(senior);
    }

    List<String> getElective(){
        List<String> electives = new ArrayList<>(Arrays.asList("SPANISH 1", "SPANISH 2","FRENCH 1", "FRENCH 2",
                "MUSIC 1", "MUSIC 2", "ART 1", "ART 2", "DIGITAL ART", "WOOD SHOP", "CULINARY", "CERAMICS"));
        Random rand = new Random();
        List<String> ans = new ArrayList<>();
        for(int i =0; i < 5;i++){
            int index = rand.nextInt(electives.size());
            String c = electives.get(index);
            ans.add(c);
            electives.remove(c);
        }
        return ans;
    }

    List<Boolean> getPreferred(){
        List<Boolean> ans = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i < 8; i++){
            if(rand.nextInt(10) > 8)
                ans.add(true);
            else
                ans.add(false);
        }
        return ans;
    }
}
