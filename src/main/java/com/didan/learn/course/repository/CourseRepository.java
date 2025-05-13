package com.didan.learn.course.repository;

import com.didan.learn.course.model.entity.CoursesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends MongoRepository<CoursesEntity, String> {

}
