package com.spring.cucumber.exceptions.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotFoundError extends SubError {
    public String path;
    public String message;
}