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
@Entity(name = "lessons")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LessonsEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "course_id")
  private CoursesEntity courseLesson;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  @Column(name = "video_url")
  @Lob
  private String videoUrl;

  @Column(name = "image_url")
  @Lob
  private String imageUrl;

  @Column(name = "order")
  private Integer order;
}
