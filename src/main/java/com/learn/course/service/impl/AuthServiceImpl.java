package com.learn.course.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learn.course.constant.StatusCodeConstant;
import com.learn.course.exception.ResourceAlreadyExistsException;
import com.learn.course.model.Status;
import com.learn.course.model.dto.request.LoginRequestDTO;
import com.learn.course.model.dto.request.SignupRequestDTO;
import com.learn.course.model.dto.response.GeneralResponse;
import com.learn.course.model.dto.response.LoginResponseDTO;
import com.learn.course.model.dto.response.SignupResponseDTO;
import com.learn.course.model.entity.UsersEntity;
import com.learn.course.repository.UserRepository;
import com.learn.course.service.IAuthService;
import com.learn.course.util.JwtUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
                                .data(SignupResponseDTO.builder().build())
                                .build();
        }
}
