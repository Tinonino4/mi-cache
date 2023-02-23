package com.micache.application.usecases.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.micache.domain.exception.InvalidEmailException;
import com.micache.domain.model.User;
import com.micache.infrastructure.adapters.UserRepository;
import com.micache.infrastructure.adapters.input.rest.model.RegisterRequest;
import com.micache.infrastructure.adapters.jms.RegisterNotification;
import com.micache.security.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.micache.security.jwt.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class RegisterUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private UserMapper mapper = new UserMapper();

    private JwtService jwtService = new JwtService();

    @Mock
    private RegisterNotification registerNotification;

    private RegisterUserUseCaseImpl underTest =
            new RegisterUserUseCaseImpl(userRepository,
                    passwordEncoder,
                    mapper,
                    jwtService,
                    registerNotification);

    @Test
    void givenReqWIthInvalidEmailThrowException() throws JsonProcessingException {
        RegisterRequest request = invalidEmailRequest();
        String expectedMessage = "Invalid Email";
        try {
            underTest.execute(request);
        } catch (InvalidEmailException ex) {
            assertTrue(ex.getMessage().equals(expectedMessage));
        }
    }

    private RegisterRequest invalidEmailRequest() {
        return RegisterRequest.builder()
                .email("invalidEmail")
                .firstname("name")
                .lastname("lastname")
                .password("password")
                .build();
    }
}