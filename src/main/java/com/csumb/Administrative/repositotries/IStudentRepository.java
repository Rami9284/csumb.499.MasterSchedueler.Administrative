package com.csumb.Administrative.repositotries;

import com.csumb.Administrative.entities.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IStudentRepository extends MongoRepository<Student,String> {

    Optional<Student> findById(String id);

    List<Student> findAll();

    Student findByName(String name);
    
}

