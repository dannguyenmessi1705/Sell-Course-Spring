package com.learn.course.constant;

import lombok.Getter;

@Getter
public enum RoleConstant {
  ROLE_ADMIN("ADMIN"),
  ROLE_STUDENT("STUDENT"),
  ROLE_TEACHER("INSTRUCTOR");

  private final String role;

  RoleConstant(String role) {
    this.role = role;
  }
}
