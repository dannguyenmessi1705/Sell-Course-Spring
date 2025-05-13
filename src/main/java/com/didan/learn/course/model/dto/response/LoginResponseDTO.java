package com.didan.learn.course.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginResponseDTO {
    private String accessToken;
    private String accessTokenExpiredAt;
    private String refreshToken;
    private String refreshTokenExpiredAt;
}
