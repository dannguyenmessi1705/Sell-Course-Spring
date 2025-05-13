package com.didan.learn.course.model.entity;

import jakarta.persistence.Id;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UsersEntity extends BaseEntity {

  @Id
  private String id;

  @Field(name = "username")
  private String username;

  @Field(name = "fullname")
  private String fullName;

  @Field(name = "email")
  private String email;

  @Field(name = "password")
  private String password;

  @Field(name = "role")
  private String role;

  @Field(name = "date_of_birth")
  private LocalDate dateOfBirth;

  @Field(name = "avatar_url")
  private String avatarUrl;

  @DBRef
  private CoursesEntity course;

  @DBRef
  private List<EnrollmentsEntity> enrollments;

  @DBRef
  private List<OrdersEntity> orders;

  @DBRef
  private List<ReviewsEntity> reviews;
}
