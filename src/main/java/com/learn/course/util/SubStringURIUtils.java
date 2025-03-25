package com.learn.course.util;

public class SubStringURIUtils {

  public static String cutURI(String uri) {
    return uri.substring(uri.indexOf("=") + 1);
  }
}
