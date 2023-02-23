package com.micache.domain.exception;

public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException(final String errorMessage) {
        super(errorMessage);
    }
}
