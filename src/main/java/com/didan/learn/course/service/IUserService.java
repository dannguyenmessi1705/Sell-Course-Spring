package com.didan.learn.course.service;

import com.didan.learn.course.model.dto.request.UserUpdateRequestDTO;
import com.didan.learn.course.model.dto.response.GeneralResponse;
import com.didan.learn.course.model.dto.response.UserInfoResponseDTO;

/**
 * @author dannd1
 * @since 3/27/2025
 */
public interface IUserService {
  GeneralResponse<UserInfoResponseDTO> getUserInfo(String userId);
  GeneralResponse<UserInfoResponseDTO> updateUserInfo(UserUpdateRequestDTO requestDTO);
}
