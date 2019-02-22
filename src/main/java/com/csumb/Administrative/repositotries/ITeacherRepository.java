package com.csumb.Administrative.repositotries;

import java.util.List;
import java.util.Optional;

import com.csumb.Administrative.entities.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import javax.management.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacherRepository extends MongoRepository<Teacher,String> {
  
    //Finding by ID
  @Override
  default Optional<Teacher> findById(String id) {
      return null;
  }

  // delete query
  void delete(Teacher s);

  void deleteById(String id);

  void deleteAll( List<Teacher> s );

  // add query
  Teacher insert(Teacher s);

  List<Teacher> insert(List<Teacher> s);
  
  // update query
  Teacher save(Teacher s);

  Teacher saveAll(Teacher s );
}
