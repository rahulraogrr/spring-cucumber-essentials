package com.spring.cucumber.exceptions.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InternalServerError extends SubError {
    private String message;
}