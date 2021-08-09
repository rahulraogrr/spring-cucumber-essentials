package com.spring.cucumber.exceptions.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class InternalServerError extends SubError {
    @Schema(title = "defaultMsg",
            description = "defaultMsg",
            example = "Internal Server Error. We have logged the error and we will work hard to make it right")
    private String defaultMsg;
}