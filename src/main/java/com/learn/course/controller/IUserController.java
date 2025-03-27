package com.learn.course.controller;

import com.learn.course.model.dto.request.LoginRequestDTO;
import com.learn.course.model.dto.request.UserUpdateRequestDTO;
import com.learn.course.model.dto.response.GeneralResponse;
import com.learn.course.model.dto.response.UserInfoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dannd1
 * @since 3/27/2025
 */
@RequestMapping("/user")
@Tag(name = "User", description = "User API")
@Validated
public interface IUserController {
  @Operation(
      summary = "Get user info", description = "Get information of user",
      responses = {
      @ApiResponse(responseCode = "200", description = "Get success", content = @Content(schema = @Schema(implementation = GeneralResponse.class))),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = GeneralResponse.class)))
  })
  @GetMapping("info/{userId}")
  ResponseEntity<GeneralResponse<UserInfoResponseDTO>> getUserInfo(@NotBlank(message = "userId not blank") @PathVariable("userId") String userId);

  @Operation(
      summary = "Update user", description = "Update information of user",
      responses = {
          @ApiResponse(responseCode = "200", description = "Get success", content = @Content(schema = @Schema(implementation = GeneralResponse.class))),
          @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = GeneralResponse.class)))
      },
      security = {
          @SecurityRequirement(name = "Bearer Authentication")
      }
  )
  @PutMapping("update")
  ResponseEntity<GeneralResponse<UserInfoResponseDTO>> updateUserInfo(@Valid @RequestBody UserUpdateRequestDTO requestDTO);
}
