package com.learn.course.service.impl;

import com.learn.course.constant.RoleConstant;
import com.learn.course.constant.StatusCodeConstant;
import com.learn.course.exception.BadRequestException;
import com.learn.course.exception.ResourceAlreadyExistsException;
import com.learn.course.exception.ResourceNotFoundException;
import com.learn.course.model.Status;
import com.learn.course.model.dto.request.GrantRoleRequestDTO;
import com.learn.course.model.dto.request.LoginRequestDTO;
import com.learn.course.model.dto.request.RevokeRoleRequestDTO;
import com.learn.course.model.dto.request.SignupRequestDTO;
import com.learn.course.model.dto.response.GeneralResponse;
import com.learn.course.model.dto.response.LoginResponseDTO;
import com.learn.course.model.dto.response.SignupResponseDTO;
import com.learn.course.model.entity.UsersEntity;
import com.learn.course.repository.UserRepository;
import com.learn.course.service.IAuthService;
import com.learn.course.util.JwtUtils;
import com.learn.course.util.ObjectMapperUtils;
import com.learn.course.util.ValidateUtils;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

  private final JwtUtils jwtUtils;
  private final UserDetailsService userDetailsService;
  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  @Override
  public GeneralResponse<LoginResponseDTO> login(LoginRequestDTO requestDTO) {
    log.info("Verifying credentials");
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(requestDTO.getUsername(),
            requestDTO.getPassword()));
    String accessToken = jwtUtils.createAccessToken(authentication);
    String refreshToken = jwtUtils.createRefreshToken(authentication);
    log.info("Generating tokens");
    LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder()
        .accessToken(accessToken)
        .accessTokenExpiredAt(jwtUtils.getExpirationDate(accessToken))
        .refreshToken(refreshToken)
        .refreshTokenExpiredAt(jwtUtils.getExpirationDate(refreshToken))
        .build();
    Status status = Status.builder()
        .code(StatusCodeConstant.SUCCESS.getCode())
        .message(StatusCodeConstant.SUCCESS.getMessage())
        .timestamp(LocalDateTime.now().toString())
        .build();
    log.info("Login successful");
    return GeneralResponse.<LoginResponseDTO>builder()
        .data(loginResponseDTO)
        .status(status)
        .build();
  }

  @Override
  public GeneralResponse<SignupResponseDTO> signup(SignupRequestDTO requestDTO) {
    log.info("Verifying request");
    if (userRepository.findUsersEntityByUsername(requestDTO.getUsername()).isPresent()) {
      throw new ResourceAlreadyExistsException("Username already exists");
    }
    if (userRepository.findUsersEntityByEmail(requestDTO.getEmail()).isPresent()) {
      throw new ResourceAlreadyExistsException("Email already exists");
    }
    log.info("Creating user");
    UsersEntity user = UsersEntity.builder()
        .username(requestDTO.getUsername())
        .password(passwordEncoder.encode(requestDTO.getPassword()))
        .fullName(requestDTO.getFullname())
        .email(requestDTO.getEmail())
        .dateOfBirth(requestDTO.getDateOfBirth())
        .avatarUrl(requestDTO.getAvatarUrl())
        .build();

    userRepository.save(user);

    return GeneralResponse.<SignupResponseDTO>builder()
        .data(ObjectMapperUtils.map(user, SignupResponseDTO.class))
        .build();
  }

  @Override
  public GeneralResponse<Object> grantRole(GrantRoleRequestDTO requestDTO) {
    log.info("Verifying request");
    UsersEntity user = userRepository.findById(requestDTO.getUserId())
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    if (!ValidateUtils.isValidRole(requestDTO.getRole())) {
      throw new BadRequestException("Invalid role");
    }
    user.setRole(requestDTO.getRole().toUpperCase());
    userRepository.save(user);
    log.info("Role granted successfully");
    Status status = Status.builder()
        .code(StatusCodeConstant.SUCCESS.getCode())
        .message(StatusCodeConstant.SUCCESS.getMessage())
        .timestamp(LocalDateTime.now().toString())
        .build();
    return GeneralResponse.<Object>builder()
        .data(user)
        .status(status)
        .build();
  }

  @Override
  public GeneralResponse<Object> revokeRole(RevokeRoleRequestDTO requestDTO) {
    log.info("Verifying request");
    String role = requestDTO.getRole().toUpperCase();
    UsersEntity user = userRepository.findById(requestDTO.getUserId())
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    if (!ValidateUtils.isValidRole(role)) {
      throw new BadRequestException("Invalid role");
    }
    if (role.equals(RoleConstant.ROLE_ADMIN.getRole())) {
      throw new BadRequestException("Cannot revoke admin role");
    }
    if (!user.getRole().equals(role)) {
      throw new BadRequestException("User does not have this role");
    }
    user.setRole(RoleConstant.ROLE_STUDENT.getRole());
    userRepository.save(user);
    log.info("Role revoked successfully");
    Status status = Status.builder()
        .code(StatusCodeConstant.SUCCESS.getCode())
        .message(StatusCodeConstant.SUCCESS.getMessage())
        .timestamp(LocalDateTime.now().toString())
        .build();
    return GeneralResponse.<Object>builder()
        .data(user)
        .status(status)
        .build();
  }

  @Override
  public GeneralResponse<Object> refreshToken() {
    log.info("Verifying request");
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      throw new BadRequestException("User not authenticated");
    }
    String refreshToken = ((Jwt) authentication.getCredentials()).getTokenValue();
    if (!jwtUtils.isRefreshToken(refreshToken)) {
      throw new BadRequestException("Invalid refresh token");
    }
    log.info("Generating tokens");
    String newAccessToken = jwtUtils.createAccessToken(authentication);
    String newRefreshToken = jwtUtils.createRefreshToken(authentication);
    Status status = Status.builder()
        .code(StatusCodeConstant.SUCCESS.getCode())
        .message(StatusCodeConstant.SUCCESS.getMessage())
        .timestamp(LocalDateTime.now().toString())
        .build();
    LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder()
        .accessToken(newAccessToken)
        .accessTokenExpiredAt(jwtUtils.getExpirationDate(newAccessToken))
        .refreshToken(newRefreshToken)
        .refreshTokenExpiredAt(jwtUtils.getExpirationDate(newRefreshToken))
        .build();
    return GeneralResponse.<Object>builder()
        .data(loginResponseDTO)
        .status(status)
        .build();
  }
}
