package com.csumb.Administrative.repositotries;

import com.csumb.Administrative.entities.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacherRepository extends MongoRepository<Teacher,String> {


}
