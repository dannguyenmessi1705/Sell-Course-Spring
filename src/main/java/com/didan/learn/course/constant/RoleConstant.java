package com.didan.learn.course.constant;

import lombok.Getter;

@Getter
public enum RoleConstant {
  ROLE_ADMIN("ADMIN"),
  ROLE_STUDENT("STUDENT"),
  ANONYMOUS_USER("anonymousUser"),
  ROLE_TEACHER("INSTRUCTOR");

  private final String role;

  RoleConstant(String role) {
    this.role = role;
  }
}
