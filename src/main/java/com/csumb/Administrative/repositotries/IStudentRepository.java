package com.csumb.Administrative.repositotries;

import java.util.List;
import java.util.Optional;

import com.csumb.Administrative.entities.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IStudentRepository extends MongoRepository<Student,String> {

    // delete query
    void delete(Student s);

    void deleteById(Student s);// ID type?

    void deleteAll( List<Student> s );

    // add query
    Student insert(Student s);

    List<Student> insert(List<Student> s);
    
    // update query
    Student save(Student s);

    Student saveAll(Student s );
    Optional<Student> findById(String id);

    List<Student> findAll();

    Student findByName(String name);
    
}

