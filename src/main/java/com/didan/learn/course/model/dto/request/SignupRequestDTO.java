package com.didan.learn.course.model.dto.request;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "SignupRequestDTO", description = "Signup request")
public class SignupRequestDTO {
    @Schema(name = "id", description = "User ID")
    private Long id;

    @Schema(name = "username", description = "Username")
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @Schema(name = "fullname", description = "Full name")
    @NotBlank(message = "Fullname is required")
    @Size(min = 3, max = 50, message = "Fullname must be between 3 and 50 characters")
    private String fullname;

    @Schema(name = "email", description = "Email")
    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;

    @Schema(name = "dateOfBirth", description = "Date of birth")
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Schema(name = "password", description = "Password")
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number and one special character")
    private String password;

    @Schema(name = "avatarUrl", description = "Avatar URL")
    private String avatarUrl;
}
