package com.learn.course.repository;

import com.learn.course.model.entity.CoursesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CoursesEntity, Long> {

}
