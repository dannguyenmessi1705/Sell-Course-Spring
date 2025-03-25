package com.learn.course.util;

import java.util.regex.Pattern;

public class ValidateUtils {

  private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
  private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

  public static boolean isValidEmail(String email) {
    return EMAIL_PATTERN.matcher(email).matches();
  }

}
