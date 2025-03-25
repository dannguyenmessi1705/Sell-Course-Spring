package com.learn.course.repository;

import com.learn.course.model.entity.UsersEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {

  Optional<UsersEntity> findUsersEntityByUsername(String username);

  Optional<UsersEntity> findUsersEntityByEmail(String email);
}
