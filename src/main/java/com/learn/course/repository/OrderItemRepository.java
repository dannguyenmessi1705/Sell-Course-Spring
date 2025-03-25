package com.learn.course.repository;

import com.learn.course.model.entity.OrderItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemsEntity, Long> {

}
