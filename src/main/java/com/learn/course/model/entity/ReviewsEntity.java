package com.learn.course.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "reviews")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewsEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "student_id")
  private UsersEntity studentReview;

  @ManyToOne
  @JoinColumn(name = "course_id")
  private CoursesEntity courseReview;

  @Column(name = "rating")
  private Integer rating;

  @Column(name = "comment")
  @Lob
  private String comment;
}
