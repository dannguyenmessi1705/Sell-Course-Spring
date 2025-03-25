package com.learn.course.util;

import java.time.Instant;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUtils {

  @Value("${spring.application.name}")
  private String issuer;
  @Value("${course.auth.access.expire.minutes}")
  private Long expireMinutes;
  @Value("${course.auth.refresh.expire.minutes}")
  private Long refreshExpireMinutes;

  private final JwtEncoder jwtEncoder;
  private final JwtDecoder jwtDecoder;

  public String createAccessToken(Authentication authentication) {
    JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer(issuer)
        .issuedAt(Instant.now())
        .expiresAt(Instant.now().plusSeconds(60 * expireMinutes))
        .subject(authentication.getName())
        .claim("scope", createScope(authentication))
        .claim("type", "access_token")
        .build();
    JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(claims);
    return jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
  }

  public String createRefreshToken(Authentication authentication) {
    JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer(issuer)
        .issuedAt(Instant.now())
        .expiresAt(Instant.now().plusSeconds(60 * refreshExpireMinutes))
        .subject(authentication.getName())
        .claim("type", "refresh_token")
        .build();
    JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(claims);
    return jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
  }

  private String createScope(Authentication authentication) {
    return authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(" "));
  }

  public String getSubject(String token) {
    Jwt jwt = jwtDecoder.decode(token);
    return jwt.getSubject();
  }

  public boolean isAccessToken(String token) {
    Jwt jwt = jwtDecoder.decode(token);
    return "access_token".equals(jwt.getClaim("type"));
  }

  public boolean isRefreshToken(String token) {
    Jwt jwt = jwtDecoder.decode(token);
    return "refresh_token".equals(jwt.getClaim("type"));
  }

  public String getExpirationDate(String token) {
    Jwt jwt = jwtDecoder.decode(token);
    return jwt.getExpiresAt() != null ? jwt.getExpiresAt().toString() : null;
  }

}
