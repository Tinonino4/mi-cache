package com.micache.domain.model;

import com.micache.domain.exception.InvalidEmailException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class UserTest {

    private final User underTest = new User();
    @Test
    void givenValidEmailReturnTrue() {
        String emailValid = "email@valid.com";
        underTest.setEmail(emailValid);
        boolean valid = underTest.isEmailValid();
        assertTrue(valid);
    }

    @Test
    void givenInvalidEmailThrowException() {
        String emailInvalid = "invalid";
        underTest.setEmail(emailInvalid);
        Exception exception = assertThrows(InvalidEmailException.class, underTest::isEmailValid);

        String expectedMessage = "Invalid Email";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}