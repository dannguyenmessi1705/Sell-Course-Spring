package com.learn.course.repository;

import com.learn.course.model.entity.ReviewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewsEntity, Long> {

}
