package com.spring.cucumber.exceptions.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonPropertyOrder({"object","field","rejectedValue","defaultMsg"})
public class BadRequestError extends SubError {
    @Schema(title = "object", description = "object", example = "User")
    private String object;

    @Schema(title = "field", description = "field", example = "username")
    private String field;

    @Schema(title = "rejectedValue", description = "rejectedValue", example = "rahul")
    private Object rejectedValue;

    @Schema(title = "defaultMsg", description = "defaultMsg", example = "username must be a minimum of 6 and maximum of 12 Characters")
    private String defaultMsg;
}
