package com.spring.cucumber.exceptions.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NotFoundError extends SubError {
    @Schema(title = "path", description = "path", example = "/api/v1/users/")
    public String path;

    @Schema(title = "defaultMsg", description = "defaultMsg", example = "User was not found for parameters rahulraogrr")
    public String defaultMsg;
}