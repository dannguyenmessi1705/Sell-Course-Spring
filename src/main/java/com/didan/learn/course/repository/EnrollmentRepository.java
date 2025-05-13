package com.didan.learn.course.repository;

import com.didan.learn.course.model.entity.EnrollmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentsEntity, Long> {

}
