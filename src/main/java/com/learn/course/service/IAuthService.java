package com.learn.course.service;

import com.learn.course.model.dto.request.LoginRequestDTO;
import com.learn.course.model.dto.request.SignupRequestDTO;
import com.learn.course.model.dto.response.GeneralResponse;
import com.learn.course.model.dto.response.LoginResponseDTO;
import com.learn.course.model.dto.response.SignupResponseDTO;

public interface IAuthService {
    GeneralResponse<LoginResponseDTO> login(LoginRequestDTO requestDTO);

    GeneralResponse<SignupResponseDTO> signup(SignupRequestDTO requestDTO);
}
