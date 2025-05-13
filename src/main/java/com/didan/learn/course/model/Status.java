package com.didan.learn.course.model;

import com.didan.learn.course.filter.RequestContext;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Status {
    private int code;
    private String message;
    private String timestamp;
    @Builder.Default
    private String path = RequestContext.getRequest().getRequestURI();
}
