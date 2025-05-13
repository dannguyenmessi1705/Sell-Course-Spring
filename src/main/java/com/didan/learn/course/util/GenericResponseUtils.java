package com.didan.learn.course.util;

import com.didan.learn.course.constant.StatusCodeConstant;
import com.didan.learn.course.filter.RequestContext;
import com.didan.learn.course.model.Status;
import java.time.LocalDateTime;
import lombok.experimental.UtilityClass;

/**
 * @author dannd1
 * @since 3/27/2025
 */
@UtilityClass
public class GenericResponseUtils {
  public static Status genStatusSuccess() {
    return Status.builder()
        .code(StatusCodeConstant.SUCCESS.getCode())
        .message(StatusCodeConstant.SUCCESS.getMessage())
        .timestamp(LocalDateTime.now().toString())
        .path(RequestContext.getRequest().getRequestURI())
        .build();
  }
}
