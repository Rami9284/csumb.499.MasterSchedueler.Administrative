package com.csumb.Administrative.repositotries;

import com.csumb.Administrative.entities.Section;
import com.csumb.Administrative.entities.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISectionRepository  extends MongoRepository<Section,String> {

    List<Section> findAllByClassName(String className);
    List<Section> findAllByPeriodNum(int periodNum);

}
