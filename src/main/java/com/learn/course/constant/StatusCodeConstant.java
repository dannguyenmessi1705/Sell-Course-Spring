package com.learn.course.constant;

import lombok.Getter;

@Getter
public enum StatusCodeConstant {
    SUCCESS(200, "Success"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int code;
    private final String message;

    StatusCodeConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
