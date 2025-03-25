package com.learn.course.controller;

import com.learn.course.model.dto.request.GrantRoleRequestDTO;
import com.learn.course.model.dto.request.LoginRequestDTO;
import com.learn.course.model.dto.request.RevokeRoleRequestDTO;
import com.learn.course.model.dto.request.SignupRequestDTO;
import com.learn.course.model.dto.response.GeneralResponse;
import com.learn.course.model.dto.response.LoginResponseDTO;
import com.learn.course.model.dto.response.SignupResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("${spring.application.name}/auth")
@Tag(name = "Auth", description = "Authentication API")
@Validated
public interface IAuthController {

  @Operation(summary = "Login", description = "Login to the system", requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = LoginRequestDTO.class))), responses = {
      @ApiResponse(responseCode = "200", description = "Login successful", content = @Content(schema = @Schema(implementation = GeneralResponse.class))),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = GeneralResponse.class)))
  })
  @PostMapping("/login")
  public ResponseEntity<GeneralResponse<LoginResponseDTO>> login(
      @Valid @org.springframework.web.bind.annotation.RequestBody LoginRequestDTO request);

  @Operation(summary = "Signup", description = "Signup to the system", requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = SignupRequestDTO.class))), responses = {
      @ApiResponse(responseCode = "201", description = "Signup successful", content = @Content(schema = @Schema(implementation = GeneralResponse.class))),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = GeneralResponse.class)))
  })
  @PostMapping("/signup")
  public ResponseEntity<GeneralResponse<SignupResponseDTO>> signup(
      @Valid @org.springframework.web.bind.annotation.RequestBody SignupRequestDTO request);

  @Operation(summary = "Grant Role", description = "Grant role to the user", requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = GrantRoleRequestDTO.class))), responses = {
      @ApiResponse(responseCode = "200", description = "Role granted successfully", content = @Content(schema = @Schema(implementation = GeneralResponse.class))),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = GeneralResponse.class)))
  }, security = {
      @SecurityRequirement(name = "Bearer Authentication")
  })
  @PutMapping("/grant-role")
  public ResponseEntity<GeneralResponse<Object>> grantRole(
      @Valid @org.springframework.web.bind.annotation.RequestBody GrantRoleRequestDTO request);

  @Operation(summary = "Revoke Role", description = "Revoke role from the user", requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = RevokeRoleRequestDTO.class))), responses = {
      @ApiResponse(responseCode = "200", description = "Role revoked successfully", content = @Content(schema = @Schema(implementation = GeneralResponse.class))),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = GeneralResponse.class)))
  }, security = {
      @SecurityRequirement(name = "Bearer Authentication")
  })
  @PutMapping("/revoke-role")
  public ResponseEntity<GeneralResponse<Object>> revokeRole(
      @Valid @org.springframework.web.bind.annotation.RequestBody RevokeRoleRequestDTO request);

  @Operation(summary = "Refresh Token", description = "Refresh token", responses = {
      @ApiResponse(responseCode = "200", description = "Token refreshed successfully", content = @Content(schema = @Schema(implementation = GeneralResponse.class))),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = GeneralResponse.class)))
  }, security = {
      @SecurityRequirement(name = "Bearer Authentication")
  })
  @PostMapping("/refresh-token")
  public ResponseEntity<GeneralResponse<Object>> refreshToken();
  
}
