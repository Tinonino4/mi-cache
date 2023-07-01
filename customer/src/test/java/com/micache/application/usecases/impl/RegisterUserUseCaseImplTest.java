package com.micache.application.usecases.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.micache.application.usecases.SendRegisterConfirmationUseCase;
import com.micache.application.usecases.mapper.ConfirmationTokenMapper;
import com.micache.domain.exception.InvalidEmailException;
import com.micache.domain.exception.UserAlreadyExistsException;
import com.micache.domain.model.Role;
import com.micache.infrastructure.adapters.input.rest.model.RegisterResponse;
import com.micache.infrastructure.adapters.output.repository.UserRepository;
import com.micache.infrastructure.adapters.input.rest.model.RegisterRequest;
import com.micache.infrastructure.adapters.output.repository.entity.ConfirmationTokenEntity;
import com.micache.infrastructure.adapters.output.repository.ConfirmationTokenRepository;
import com.micache.security.jwt.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.micache.security.jwt.mapper.UserMapper;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RegisterUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private final UserMapper userMapper = new UserMapper();
    private final ConfirmationTokenMapper confirmationTokenMapper = new ConfirmationTokenMapper();

    @Mock
    private SendRegisterConfirmationUseCase sendRegisterConfirmationUseCase;


    RegisterUserUseCaseImpl underTest;

    @BeforeEach
    public void setUp() {
        underTest =
                new RegisterUserUseCaseImpl(userRepository,
                        confirmationTokenRepository,
                        passwordEncoder,
                        userMapper,
                        confirmationTokenMapper,
                        sendRegisterConfirmationUseCase);
    }

    @Test
    void givenGoodRequestCreateNewUser() throws IOException {
        // given
        UserDetails userDetailsMock = Mockito.mock(UserDetails.class);
        Mockito.when(userDetailsMock.getUsername()).thenReturn("testUser");

        RegisterRequest request = buildGoodUser();
        UserEntity userSaved = createUserSaved();
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.saveAndFlush(any())).thenReturn(userSaved);
        ConfirmationTokenEntity confirmationToken = createCofirmationToken();
        when(confirmationTokenRepository.saveAndFlush(any())).thenReturn(confirmationToken);
        doNothing().when(sendRegisterConfirmationUseCase).execute(any());
        // when
        RegisterResponse response = underTest.execute(request);
        // then
        assertNotNull(response.getToken());
    }

    private UserEntity createUserSaved() {
        return UserEntity.builder()
                .id(UUID.fromString("0f14d0ab-9605-4a62-a9e4-5ed26688389b"))
                .created_at(new Date())
                .role(Role.CACHER)
                .password("password")
                .email("mail@mail.com")
                .firstname("firstname")
                .lastname("lastname")
                .build();
    }

    private ConfirmationTokenEntity createCofirmationToken() {
        UUID token = UUID.fromString("cb0ac05b-d825-4a1e-9a28-6b397c1a0324");
        return ConfirmationTokenEntity.builder()
                .id(UUID.randomUUID())
                .confirmationToken(token)
                .createdAt(new Date())
                .idUser(UUID.randomUUID()).build();
    }

    private RegisterRequest buildGoodUser() {
        return RegisterRequest.builder()
                .email("gooduser@mail.com")
                .firstname("name")
                .lastname("lastname")
                .password("password")
                .build();
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
        } catch (UserAlreadyExistsException | IOException ex) {
            // then
            assertEquals(ex.getMessage(), expectedMessage);
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
        } catch (InvalidEmailException | IOException ex) {
            assertEquals(ex.getMessage(), expectedMessage);
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