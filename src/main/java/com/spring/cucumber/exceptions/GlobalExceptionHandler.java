package com.spring.cucumber.exceptions;

import com.spring.cucumber.exceptions.custom.NotFoundException;
import com.spring.cucumber.exceptions.models.ApiErrorResponse;
import com.spring.cucumber.exceptions.models.BadRequestError;
import com.spring.cucumber.exceptions.models.NotFoundError;
import com.spring.cucumber.exceptions.models.SubError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<ApiErrorResponse> handleInternalServerError(Exception e){
		log.error("UNHANDLED EXCEPTION: ",e);
		return new ResponseEntity<>(new ApiErrorResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = {NotFoundException.class})
	public ResponseEntity<ApiErrorResponse> handleNotFoundException(NotFoundException e, HttpServletRequest request){
		log.error("Not Found Exception : ");
		ApiErrorResponse response = new ApiErrorResponse(
				HttpStatus.NOT_FOUND.value(),
				new Date(),
				Arrays.asList(new NotFoundError(request.getRequestURI(),e.getMessage()))
		);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 *
	 * @param e - {@link MethodArgumentNotValidException}
	 * @return
	 */
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<ApiErrorResponse> handleValidationErrors(MethodArgumentNotValidException e){
		log.error("Validation Error");
		List<SubError> badRequestErrors = new ArrayList<>();
		List<FieldError> fieldErrors = e.getFieldErrors();

		fieldErrors.stream().forEach(fieldError -> {
			BadRequestError error = new BadRequestError();
			error.setField(fieldError.getField());
			error.setObject(fieldError.getObjectName());
			error.setMessage(fieldError.getDefaultMessage());
			error.setRejectedValue(fieldError.getRejectedValue());
			badRequestErrors.add(error);
		});

		ApiErrorResponse response = new ApiErrorResponse(
				HttpStatus.BAD_REQUEST.value(),
				new Date(),
				badRequestErrors
		);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}