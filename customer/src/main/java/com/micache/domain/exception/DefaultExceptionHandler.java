package com.micache.domain.exception;

import com.micache.domain.exception.ApiError;
import com.micache.domain.exception.InvalidEmailException;
import com.micache.domain.exception.UserAlreadyExistsException;
import org.apache.http.HttpStatus;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_UNPROCESSABLE_ENTITY;

@ControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleUserAlreadyExistsException(UserAlreadyExistsException e, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.SC_CONFLICT,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiError, org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_CONFLICT));
    }

    @ExceptionHandler(value = {InvalidEmailException.class})
    public ResponseEntity<ApiError> handleInvalidEmailException(InvalidEmailException e, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                e.getMessage(),
                SC_UNPROCESSABLE_ENTITY,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiError, org.springframework.http.HttpStatus.valueOf(SC_UNPROCESSABLE_ENTITY));
    }

    @ExceptionHandler(value = {ConfirmationTokenNotFound.class})
    public ResponseEntity<ApiError> handleConfirmationTokenNotFound(ConfirmationTokenNotFound e, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                e.getMessage(),
                SC_NOT_FOUND,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiError, org.springframework.http.HttpStatus.valueOf(SC_NOT_FOUND));
    }
}
