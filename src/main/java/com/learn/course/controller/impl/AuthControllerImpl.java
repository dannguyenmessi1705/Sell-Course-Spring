package com.learn.course.controller.impl;

import com.learn.course.controller.IAuthController;
import com.learn.course.model.dto.request.LoginRequestDTO;
import com.learn.course.model.dto.request.SignupRequestDTO;
import com.learn.course.model.dto.response.GeneralResponse;
import com.learn.course.model.dto.response.LoginResponseDTO;
import com.learn.course.model.dto.response.SignupResponseDTO;
import com.learn.course.service.IAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthControllerImpl implements IAuthController {

  private final IAuthService authService;

  @Override
  public ResponseEntity<GeneralResponse<LoginResponseDTO>> login(LoginRequestDTO request) {
    log.info("Login request: {}", request);
    GeneralResponse<LoginResponseDTO> response = authService.login(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GeneralResponse<SignupResponseDTO>> signup(SignupRequestDTO request) {
    log.info("Signup request: {}", request);
    GeneralResponse<SignupResponseDTO> response = authService.signup(request);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
