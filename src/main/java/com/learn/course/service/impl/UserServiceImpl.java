package com.learn.course.service.impl;

import com.learn.course.constant.TrackingConstant;
import com.learn.course.exception.BadRequestException;
import com.learn.course.exception.ResourceAlreadyExistsException;
import com.learn.course.exception.ResourceNotFoundException;
import com.learn.course.filter.RequestContext;
import com.learn.course.model.dto.request.UserUpdateRequestDTO;
import com.learn.course.model.dto.response.GeneralResponse;
import com.learn.course.model.dto.response.UserInfoResponseDTO;
import com.learn.course.model.entity.UsersEntity;
import com.learn.course.repository.UserRepository;
import com.learn.course.service.IUserService;
import com.learn.course.util.GenericResponseUtils;
import com.learn.course.util.ObjectMapperUtils;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author dannd1
 * @since 3/27/2025
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

  private final UserRepository userRepository;

  @Override
  public GeneralResponse<UserInfoResponseDTO> getUserInfo(String userId) {
    try {
      Long userIdLong = Long.parseLong(userId);
      UsersEntity user = userRepository.findById(userIdLong).orElseThrow(() ->
          new ResourceNotFoundException("User not found"));

      return GeneralResponse.<UserInfoResponseDTO>builder()
          .status(GenericResponseUtils.genStatusSuccess())
          .data(ObjectMapperUtils.map(user, UserInfoResponseDTO.class))
          .build();
    } catch (Exception ex) {
      log.error("getUserInfo", ex);
      throw new BadRequestException("Invalid user id");
    }
  }

  @Override
  public GeneralResponse<UserInfoResponseDTO> updateUserInfo(UserUpdateRequestDTO requestDTO) {
    String userId = RequestContext.getRequest().getHeader(TrackingConstant.X_USER_ID.getHeaderKey());
    UsersEntity user = userRepository.findById(Long.parseLong(userId)).orElseThrow(() ->
        new ResourceNotFoundException("User not found"));
    if (StringUtils.hasText(requestDTO.getUsername())) {
      userRepository.findUsersEntityByUsernameIgnoreCase(requestDTO.getUsername())
          .ifPresent(u -> {
            throw new ResourceAlreadyExistsException(String.format("Username %s is already taken", u.getUsername()));
          });
      user.setUsername(requestDTO.getUsername());
    }
    if (StringUtils.hasText(requestDTO.getEmail())) {
      userRepository.findUsersEntityByEmailIgnoreCase(requestDTO.getEmail())
          .ifPresent(u -> {
            throw new ResourceAlreadyExistsException(String.format("Email %s is already taken", u.getEmail()));
          });
      user.setEmail(requestDTO.getEmail());
    }
    if (StringUtils.hasText(requestDTO.getFullName())) {
      user.setFullName(requestDTO.getFullName());
    }
    if (StringUtils.hasText(requestDTO.getAvatarUrl())) {
      user.setAvatarUrl(requestDTO.getAvatarUrl());
    }
    if (StringUtils.hasText(requestDTO.getDateOfBirth())) {
      LocalDate dateOfBirth = LocalDate.parse(requestDTO.getDateOfBirth());
      user.setDateOfBirth(dateOfBirth);
    }
    log.info("Update user info");
    userRepository.save(user);
    return GeneralResponse.<UserInfoResponseDTO>builder()
        .status(GenericResponseUtils.genStatusSuccess())
        .data(ObjectMapperUtils.map(user, UserInfoResponseDTO.class))
        .build();
  }
}
