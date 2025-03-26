package com.learn.course.filter;

import com.learn.course.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Order(1)
@Configuration
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {

  private final JwtUtils jwtUtils;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    log.info("Auth Filter");
    // Read token from Authentication header
    String authHeader = request.getHeader("Authorization");
    // Or get from SecurityContextHolder
    // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // String accessToken = ((Jwt) authentication.getCredentials()).getTokenValue();
    if (StringUtils.isNotEmpty(authHeader) && authHeader.startsWith("Bearer")) {
      // Extract token
      String token = authHeader.substring(7);;

      Long userId = Long.parseLong(jwtUtils.getSubject(token));
      // Set userId to Http request header with key x-user-id
      request.setAttribute("x-user-id", userId);
    }
    filterChain.doFilter(request, response);
  }
}
