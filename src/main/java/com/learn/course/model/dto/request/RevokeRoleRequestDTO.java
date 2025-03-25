package com.learn.course.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RevokeRoleRequestDTO {

  @NotNull(message = "User ID is required")
  private Long userId;

  @NotBlank(message = "Role is required")
  private String role;
}
