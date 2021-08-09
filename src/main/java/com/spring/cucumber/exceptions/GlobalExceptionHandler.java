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

		return new ResponseEntity<>(new ApiErrorResponse(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				LocalDateTime.now(),
				List.of(new InternalServerError("Internal Server Error. We have logged the error and we will work hard to make it right"))
		), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { HttpMessageNotReadableException.class })
	public ResponseEntity<ApiErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request){
		log.error("HttpMessageNotReadableException");

		return new ResponseEntity<>(new ApiErrorResponse(
				HttpStatus.BAD_REQUEST.value(),
				LocalDateTime.now(),
				List.of(new BadRequestError(null,null,null,"Required request body is missing"))
		), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { NotFoundException.class })
	public ResponseEntity<ApiErrorResponse> handleNotFoundException(NotFoundException e, HttpServletRequest request){
		log.error("Not Found Exception : ");
		ApiErrorResponse response = new ApiErrorResponse(
				HttpStatus.NOT_FOUND.value(),
				LocalDateTime.now(),
				List.of(new NotFoundError(request.getRequestURI(), e.getMessage()))
		);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
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
			e.getFieldErrors().forEach(fieldError -> {
				BadRequestError error = new BadRequestError();
				error.setObject(fieldError.getObjectName());
				error.setField(fieldError.getField());
				error.setRejectedValue(fieldError.getRejectedValue());
				error.setDefaultMsg(fieldError.getDefaultMessage());
				badRequestErrors.add(error);
			});
		}

		ApiErrorResponse response = new ApiErrorResponse(
				HttpStatus.BAD_REQUEST.value(),
				LocalDateTime.now(),
				badRequestErrors
		);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}