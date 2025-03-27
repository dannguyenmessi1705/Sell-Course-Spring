package com.learn.course.util;

import com.learn.course.auth.CustomUserPrincipal;
import com.learn.course.filter.RequestContext;
import java.time.Instant;
import java.util.Optional;
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
    CustomUserPrincipal userPrincipal = null;
    Long subId = null;
    if (authentication.getPrincipal() instanceof CustomUserPrincipal) {
      userPrincipal = (CustomUserPrincipal) authentication.getPrincipal();
      subId = userPrincipal.getUserId();
    } else {
      String accessToken = ((Jwt) authentication.getCredentials()).getTokenValue();
      subId = Long.parseLong(getSudId(accessToken));
    }
    JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer(issuer)
        .issuedAt(Instant.now())
        .expiresAt(Instant.now().plusSeconds(60 * expireMinutes))
        .subject(authentication.getName())
        .claim("scope", createScope(authentication))
        .claim("type", "access_token")
        .claim("sub-id", subId)
        .build();
    JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(claims);
    return jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
  }

  public String createRefreshToken(Authentication authentication) {
    CustomUserPrincipal userPrincipal = null;
    Long subId = null;
    if (authentication.getPrincipal() instanceof CustomUserPrincipal) {
      userPrincipal = (CustomUserPrincipal) authentication.getPrincipal();
      subId = userPrincipal.getUserId();
    } else {
      String refreshToken = ((Jwt) authentication.getCredentials()).getTokenValue();
      subId = Long.parseLong(getSudId(refreshToken));
    }
    JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer(issuer)
        .issuedAt(Instant.now())
        .expiresAt(Instant.now().plusSeconds(60 * refreshExpireMinutes))
        .subject(authentication.getName())
        .claim("type", "refresh_token")
        .claim("sub-id", subId)
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

  public String getSudId(String token) {
    Jwt jwt = jwtDecoder.decode(token);
    return jwt.getClaim("sub-id").toString();
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
    return Optional.ofNullable(jwt.getExpiresAt())
        .map(Instant::toString)
        .orElse(null);
  }

  public String getTokenFromHeader() {
    String header = RequestContext.getRequest().getHeader("Authorization");
    return header.replace("Bearer ", "");
  }

}
