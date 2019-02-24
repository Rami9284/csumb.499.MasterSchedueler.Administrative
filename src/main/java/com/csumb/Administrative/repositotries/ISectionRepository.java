package com.csumb.Administrative.repositotries;

import com.csumb.Administrative.entities.Section;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISectionRepository  extends MongoRepository<Section,String> {

}
