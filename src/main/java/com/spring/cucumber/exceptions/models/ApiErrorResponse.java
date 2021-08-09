package com.spring.cucumber.exceptions.models;

import java.time.LocalDateTime;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import com.spring.cucumber.exceptions.ClassNameResolver;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonPropertyOrder({"timestamp","status","message"})
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT,
		use = JsonTypeInfo.Id.CUSTOM,
		property = "error",
		visible = true)
@JsonTypeIdResolver(ClassNameResolver.class)
public class ApiErrorResponse {
	
	@Schema(description = "Error Status")
	private final int status;

	@Schema(description = "Error Timestamp")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private final LocalDateTime timestamp;
	
	@Schema(description = "Error Message")
	private final List<SubError> message;

}