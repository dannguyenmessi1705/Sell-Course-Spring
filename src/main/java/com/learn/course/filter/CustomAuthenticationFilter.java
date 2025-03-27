package com.learn.course.filter;

import com.learn.course.constant.RoleConstant;
import com.learn.course.constant.TrackingConstant;
import com.learn.course.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import lombok.Locked.Read;
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
    /* Read token from Authentication header
      String authHeader = request.getHeader("Authorization");
      Or get from SecurityContextHolder
      if (StringUtils.isNotEmpty(authHeader) && authHeader.startsWith("Bearer")) {
        Extract token
        String token = authHeader.substring(7);;

        Long userId = Long.parseLong(jwtUtils.getSubject(token));
        Set userId to Http request header with key x-user-id
      }
    */
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (Objects.isNull(authentication.getName()) || authentication.getName().equalsIgnoreCase(RoleConstant.ANONYMOUS_USER.getRole())) {
      filterChain.doFilter(request, response);
      return;
    }
    String token = null;
    if (authentication.getCredentials() instanceof Jwt) {
      token = ((Jwt) authentication.getCredentials()).getTokenValue();
    } else if (authentication.getCredentials() instanceof String) {
      token = (String) authentication.getCredentials();
    }
    String userId = jwtUtils.getSudId(token);
    HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(request) {
      @Override
      public String getHeader(String name) {
        if (TrackingConstant.X_REQUEST_ID.getHeaderKey().equalsIgnoreCase(name)) {
          return userId;
        }
        return super.getHeader(name);
      }
    };
    RequestContext.setRequest(requestWrapper);
    filterChain.doFilter(requestWrapper, response);
  }
}
