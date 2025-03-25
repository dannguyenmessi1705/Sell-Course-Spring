package com.learn.course.service;

import com.learn.course.model.dto.request.GrantRoleRequestDTO;
import com.learn.course.model.dto.request.LoginRequestDTO;
import com.learn.course.model.dto.request.RevokeRoleRequestDTO;
import com.learn.course.model.dto.request.SignupRequestDTO;
import com.learn.course.model.dto.response.GeneralResponse;
import com.learn.course.model.dto.response.LoginResponseDTO;
import com.learn.course.model.dto.response.SignupResponseDTO;

public interface IAuthService {
    GeneralResponse<LoginResponseDTO> login(LoginRequestDTO requestDTO);

    GeneralResponse<SignupResponseDTO> signup(SignupRequestDTO requestDTO);

    GeneralResponse<Object> grantRole(GrantRoleRequestDTO requestDTO);

    GeneralResponse<Object> revokeRole(RevokeRoleRequestDTO requestDTO);

    GeneralResponse<Object> refreshToken();
}
