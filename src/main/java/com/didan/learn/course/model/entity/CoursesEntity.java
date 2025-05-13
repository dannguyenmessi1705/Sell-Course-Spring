package com.didan.learn.course.model.entity;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Document(collection = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CoursesEntity extends BaseEntity {

  @Id
  private String id;

  @Field(name = "title")
  private String title;

  @Field(name = "description")
  private String description;

  @Field(name = "price")
  private Double price;

  @Field(name = "is_published")
  private Boolean isPublished = false;

  @Field(name = "image_url")
  private String imageUrl;

  @DBRef
  private List<EnrollmentsEntity> enrollments;

  @DBRef
  private List<LessonsEntity> lessons;

  @DBRef
  private List<OrderItemsEntity> orderItems;

  @OneToMany(mappedBy = "courseReview")
  private List<ReviewsEntity> reviews;
}
