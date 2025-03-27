package com.learn.course.controller.impl;

import com.learn.course.controller.IUserController;
import com.learn.course.model.dto.request.UserUpdateRequestDTO;
import com.learn.course.model.dto.response.GeneralResponse;
import com.learn.course.model.dto.response.UserInfoResponseDTO;
import com.learn.course.service.IUserService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dannd1
 * @since 3/27/2025
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class UserControllerImpl implements IUserController {

  private final IUserService userService;

  @Override
  public ResponseEntity<GeneralResponse<UserInfoResponseDTO>> getUserInfo(String userId) {
    log.info("getUserInfo id: {}", userId);
    GeneralResponse<UserInfoResponseDTO> response = userService.getUserInfo(userId);
    return ResponseEntity.ok(response);
  }

  @Override
  public ResponseEntity<GeneralResponse<UserInfoResponseDTO>> updateUserInfo(UserUpdateRequestDTO requestDTO) {
    log.info("updateUserInfo");
    GeneralResponse<UserInfoResponseDTO> response = userService.updateUserInfo(requestDTO);
    return ResponseEntity.ok(response);
  }
}
