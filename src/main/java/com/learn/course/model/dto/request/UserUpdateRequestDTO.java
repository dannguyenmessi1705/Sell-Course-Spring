package com.learn.course.model.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class UserUpdateRequestDTO {

  @Size(min = 2, max = 20, message = "Username must be between 3 and 20 characters")
  private String username;

  @Size(min = 3, max = 50, message = "Fullname must be between 3 and 50 characters")
  private String fullName;

  private String avatarUrl;

  @Pattern(regexp = "|^$|^.*@.*\\..*$|", message = "Email is invalid")
  private String email;

  private String dateOfBirth;
}
