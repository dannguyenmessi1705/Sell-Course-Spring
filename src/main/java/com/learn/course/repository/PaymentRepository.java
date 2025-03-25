package com.learn.course.repository;

import com.learn.course.model.entity.PaymentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentsEntity, Long> {

}
