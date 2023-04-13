package com.micache.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ConfirmationTokenNotFound extends RuntimeException {
    public ConfirmationTokenNotFound(final String errorMessage) {
        super(errorMessage);
    }
}
