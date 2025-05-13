package com.didan.learn.course.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UsersEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username")
  private String username;

  @Column(name = "fullname")
  private String fullName;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "role")
  private String role;

  @Column(name = "date_of_birth")
  private LocalDate dateOfBirth;

  @Column(name = "avatar_url")
  @Lob
  private String avatarUrl;

  @OneToOne(mappedBy = "instructor")
  private CoursesEntity course;

  @OneToMany(mappedBy = "student")
  private List<EnrollmentsEntity> enrollments;

  @OneToMany(mappedBy = "studentOrder")
  private List<OrdersEntity> orders;

  @OneToMany(mappedBy = "studentReview")
  private List<ReviewsEntity> reviews;
}
