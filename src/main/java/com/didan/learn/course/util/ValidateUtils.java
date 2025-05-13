package com.didan.learn.course.util;

import java.util.regex.Pattern;

import com.didan.learn.course.constant.RoleConstant;

public class ValidateUtils {

  private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
  private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

  public static boolean isValidEmail(String email) {
    return EMAIL_PATTERN.matcher(email).matches();
  }

  public static boolean isValidRole(String role) {
    if (role.isEmpty()) {
      return false;
    }

    return role.equalsIgnoreCase(RoleConstant.ROLE_ADMIN.getRole())
        || role.equalsIgnoreCase(RoleConstant.ROLE_STUDENT.getRole())
        || role.equalsIgnoreCase(RoleConstant.ROLE_TEACHER.getRole());
  }

}
