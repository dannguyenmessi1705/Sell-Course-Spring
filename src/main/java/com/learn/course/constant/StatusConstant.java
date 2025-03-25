package com.learn.course.constant;

import lombok.Getter;

@Getter
public enum StatusConstant {
  PENDING("PENDING"),
  COMPLETED("COMPLETED"),
  FAILED("FAILED"),
  CANCELLED("CANCELLED");

  private final String status;

  StatusConstant(String status) {
    this.status = status;
  }
}
