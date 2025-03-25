package com.learn.course.model.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "SignupResponseDTO", description = "Signup response")
public class SignupResponseDTO {
    @Schema(name = "id", description = "User ID")
    private Long id;

    @Schema(name = "username", description = "Username")
    private String username;

    @Schema(name = "fullname", description = "Full name")
    private String fullname;

    @Schema(name = "email", description = "Email")
    private String email;

    @Schema(name = "dateOfBirth", description = "Date of birth")
    private LocalDate dateOfBirth;

    @Schema(name = "avatarUrl", description = "Avatar URL")
    private String avatarUrl;

    @Schema(name = "createdTime", description = "Created time")
    private LocalDateTime createdTime;

    @Schema(name = "lastUpdatedTime", description = "Last updated time")
    private LocalDateTime lastUpdatedTime;
}
