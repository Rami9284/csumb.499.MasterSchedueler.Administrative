package com.csumb.Administrative.repositotries;

import com.csumb.Administrative.entities.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends MongoRepository<Student,String> {

//    @Query(value = "{id: $id}", delete = true)
//    void deleteById(String id);
}

