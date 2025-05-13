package com.didan.learn.course.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CoursesEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  @Lob
  private String description;

  @Column(name = "price")
  private Double price;

  @OneToOne
  @JoinColumn(name = "instructor_id")
  private UsersEntity instructor;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private CategoriesEntity category;

  @Column(name = "is_published")
  private Boolean isPublished = false;

  @Column(name = "image_url")
  @Lob
  private String imageUrl;

  @OneToMany(mappedBy = "course")
  private List<EnrollmentsEntity> enrollments;

  @OneToMany(mappedBy = "courseLesson")
  private List<LessonsEntity> lessons;

  @OneToMany(mappedBy = "courseOrderItem")
  private List<OrderItemsEntity> orderItems;

  @OneToMany(mappedBy = "courseReview")
  private List<ReviewsEntity> reviews;
}
