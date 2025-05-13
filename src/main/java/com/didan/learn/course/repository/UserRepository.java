package com.didan.learn.course.repository;

import com.didan.learn.course.model.entity.UsersEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UsersEntity, String> {

}
