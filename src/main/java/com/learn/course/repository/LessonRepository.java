package com.learn.course.repository;

import com.learn.course.model.entity.LessonsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<LessonsEntity, Long> {

}
