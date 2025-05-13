package com.didan.learn.course.service;

import com.didan.learn.course.model.dto.request.GrantRoleRequestDTO;
import com.didan.learn.course.model.dto.request.LoginRequestDTO;
import com.didan.learn.course.model.dto.request.RevokeRoleRequestDTO;
import com.didan.learn.course.model.dto.request.SignupRequestDTO;
import com.didan.learn.course.model.dto.response.GeneralResponse;
import com.didan.learn.course.model.dto.response.LoginResponseDTO;
import com.didan.learn.course.model.dto.response.SignupResponseDTO;

public interface IAuthService {
    GeneralResponse<LoginResponseDTO> login(LoginRequestDTO requestDTO);

    GeneralResponse<SignupResponseDTO> signup(SignupRequestDTO requestDTO);

    GeneralResponse<Object> grantRole(GrantRoleRequestDTO requestDTO);

    GeneralResponse<Object> revokeRole(RevokeRoleRequestDTO requestDTO);

    GeneralResponse<Object> refreshToken();
}
