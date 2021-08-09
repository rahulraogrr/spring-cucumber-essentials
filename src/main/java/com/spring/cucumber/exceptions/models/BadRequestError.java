package com.spring.cucumber.exceptions.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestError extends SubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;
}
