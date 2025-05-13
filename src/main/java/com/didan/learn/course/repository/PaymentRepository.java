package com.didan.learn.course.repository;

import com.didan.learn.course.model.entity.PaymentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentsEntity, Long> {

}
