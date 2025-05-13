package com.didan.learn.course.model.dto.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dannd1
 * @since 3/27/2025
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserInfoResponseDTO {
  private Long id;

  private String username;

  private String fullName;

  private String avatarUrl;

  private String email;

  private LocalDate dateOfBirth;

}
