package com.spring.cucumber.exceptions;

import com.spring.cucumber.exceptions.custom.NotFoundException;
import com.spring.cucumber.exceptions.models.ApiErrorResponse;
import com.spring.cucumber.exceptions.models.BadRequestError;
import com.spring.cucumber.exceptions.models.InternalServerError;
import com.spring.cucumber.exceptions.models.NotFoundError;
import com.spring.cucumber.exceptions.models.messages.BadRequestMessage;
import com.spring.cucumber.exceptions.models.messages.InternalServerMessage;
import com.spring.cucumber.exceptions.models.messages.NotFoundMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ApiErrorResponse> handleInternalServerError(Exception e){
        log.error("UNHANDLED EXCEPTION: ",e);

        InternalServerError error = new InternalServerError();
        error.setTimestamp(LocalDateTime.now());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessages(List.of(InternalServerMessage.builder().defaultMessage("Internal Server Error. We have logged the error and we will work hard to make it right").build()));

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { HttpMessageNotReadableException.class })
    public ResponseEntity<ApiErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request){
        log.error("HttpMessageNotReadableException : {}",e.getMessage());

        BadRequestError error = new BadRequestError();
        error.setTimestamp(LocalDateTime.now());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessages(List.of(BadRequestMessage.builder().defaultMessage("Required request body is missing").build()));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { NotFoundException.class })
    public ResponseEntity<ApiErrorResponse> handleNotFoundException(NotFoundException e, HttpServletRequest request){
        log.error("Not Found Exception : ");

        NotFoundError error = new NotFoundError();
        error.setTimestamp(LocalDateTime.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessages(List.of(NotFoundMessage.builder().path(request.getRequestURI()).defaultMessage(e.getMessage()).build()));

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     *
     * @param e {@link MethodArgumentNotValidException}
     * @return error {@link ApiErrorResponse}
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiErrorResponse> handleValidationErrors(MethodArgumentNotValidException e){
        log.error("Validation Error");

        List<BadRequestMessage> badRequestMessages = new ArrayList<>();

        if (e.getFieldErrors().size()>0){
            e.getFieldErrors().forEach(fieldError -> badRequestMessages.add(BadRequestMessage.builder().object(fieldError.getObjectName()).field(fieldError.getField()).rejectedValue(fieldError.getRejectedValue()).defaultMessage(fieldError.getDefaultMessage()).build()));
        }

        BadRequestError error = new BadRequestError();
        error.setTimestamp(LocalDateTime.now());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessages(badRequestMessages);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}