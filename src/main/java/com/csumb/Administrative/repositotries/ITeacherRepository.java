package com.csumb.Administrative.repositotries;

import com.csumb.Administrative.entities.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITeacherRepository extends MongoRepository<Teacher,String> {

}
