package com.csumb.Administrative.repositotries;

import java.util.List;
import java.util.Optional;

import com.csumb.Administrative.entities.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import javax.management.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacherRepository extends MongoRepository<Teacher,String> {
  
}
