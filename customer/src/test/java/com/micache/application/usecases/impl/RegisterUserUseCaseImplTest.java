package com.micache.application.usecases.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.micache.application.usecases.mapper.ConfirmationTokenMapper;
import com.micache.domain.exception.InvalidEmailException;
import com.micache.domain.exception.UserAlreadyExistsException;
import com.micache.infrastructure.adapters.UserRepository;
import com.micache.infrastructure.adapters.input.rest.model.RegisterRequest;
import com.micache.infrastructure.adapters.jms.RegisterNotification;
import com.micache.infrastructure.adapters.output.repository.entity.ConfirmationTokenRepository;
import com.micache.security.jwt.JwtService;
import com.micache.security.jwt.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.micache.security.jwt.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RegisterUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private final UserMapper userMapper = new UserMapper();
    private final ConfirmationTokenMapper confirmationTokenMapper = new ConfirmationTokenMapper();

    private final JwtService jwtService = new JwtService();

    @Mock
    private RegisterNotification registerNotification;


    RegisterUserUseCaseImpl underTest;

    @BeforeEach
    public void setUp() {
        underTest =
                new RegisterUserUseCaseImpl(userRepository,
                        confirmationTokenRepository,
                        passwordEncoder,
                        userMapper,
                        confirmationTokenMapper,
                        jwtService,
                        registerNotification);
    }
    @Test
    void givenUserAlreadyExistsThrowUserAlreadyExistsException() throws JsonProcessingException {
        // given
        String expectedMessage = "User already exists: alreadyExists@mail.com";
        RegisterRequest request = buildAlreadyExistsUser();
        Optional<UserEntity> user = Optional.of(new UserEntity());
        when(userRepository.findByEmail(anyString())).thenReturn(user);
        // when
        try {
            underTest.execute(request);
        } catch (UserAlreadyExistsException ex) {
            // then
            assertTrue(ex.getMessage().equals(expectedMessage));
        }
    }

    private RegisterRequest buildAlreadyExistsUser() {
        return RegisterRequest.builder()
                .email("alreadyExists@mail.com")
                .firstname("name")
                .lastname("lastname")
                .password("password")
                .build();
    }

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