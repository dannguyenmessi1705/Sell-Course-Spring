package com.learn.course.model.dto.response;

import com.learn.course.model.Status;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "GeneralResponse", description = "General response")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GeneralResponse<T> {
    @Schema(name = "status", description = "Status of the response")
    private Status status;

    @Schema(name = "data", description = "Data of the response")
    private T data;
}
