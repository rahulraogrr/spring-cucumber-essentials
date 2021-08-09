package com.spring.cucumber.exceptions;

import com.spring.cucumber.exceptions.custom.NotFoundException;
import com.spring.cucumber.exceptions.models.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<ApiErrorResponse> handleInternalServerError(Exception e){
		log.error("UNHANDLED EXCEPTION: ",e);

		return new ResponseEntity<>(ApiErrorResponse.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message(List.of(InternalServerError.builder().defaultMsg("Internal Server Error. We have logged the error and we will work hard to make it right").build()))
				.build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { HttpMessageNotReadableException.class })
	public ResponseEntity<ApiErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request){
		log.error("HttpMessageNotReadableException : {}",e.getMessage());

		return new ResponseEntity<>(ApiErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.timestamp(LocalDateTime.now())
				.message(List.of(BadRequestError.builder().defaultMsg("Required request body is missing").build()))
				.build(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { NotFoundException.class })
	public ResponseEntity<ApiErrorResponse> handleNotFoundException(NotFoundException e, HttpServletRequest request){
		log.error("Not Found Exception : ");

		return new ResponseEntity<>(ApiErrorResponse.builder()
				.status(HttpStatus.NOT_FOUND.value())
				.timestamp(LocalDateTime.now())
				.message(List.of(NotFoundError.builder().path(request.getRequestURI()).defaultMsg(e.getMessage()).build()))
				.build(), HttpStatus.NOT_FOUND);
	}

	/**
	 *
	 * @param e {@link MethodArgumentNotValidException}
	 * @return error {@link ApiErrorResponse}
	 */
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<ApiErrorResponse> handleValidationErrors(MethodArgumentNotValidException e){
		log.error("Validation Error");
		List<SubError> badRequestErrors = new ArrayList<>();

		if (e.getFieldErrors().size()>0){
			e.getFieldErrors().forEach(fieldError -> badRequestErrors.add(BadRequestError.builder().object(fieldError.getObjectName()).field(fieldError.getField()).rejectedValue(fieldError.getRejectedValue()).defaultMsg(fieldError.getDefaultMessage()).build()));
		}

		return new ResponseEntity<>(ApiErrorResponse.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.message(badRequestErrors).build(), HttpStatus.BAD_REQUEST);
	}
}