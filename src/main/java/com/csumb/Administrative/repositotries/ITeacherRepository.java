package com.csumb.Administrative.repositotries;

import com.csumb.Administrative.entities.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacherRepository extends MongoRepository<Teacher,String> {

    @Query (value = "{'id':'?0'}")
    
    Teacher findId(String id);


    @Query (value = "{'name':?0}")   

    Teacher findName(String name); 

    // delete query

    // add query

    // update query
}
