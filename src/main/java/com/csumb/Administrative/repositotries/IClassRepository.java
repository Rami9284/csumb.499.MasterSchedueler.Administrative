package com.csumb.Administrative.repositotries;

import com.csumb.Administrative.entities.Class;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClassRepository extends MongoRepository<Class,String> {

}
