package com.micache.application.usecases.impl;

import com.micache.application.usecases.ConfirmUserUseCase;
import com.micache.domain.exception.ConfirmationTokenNotFound;
import com.micache.infrastructure.adapters.UserRepository;
import com.micache.infrastructure.adapters.output.repository.entity.ConfirmationTokenEntity;
import com.micache.infrastructure.adapters.output.repository.entity.ConfirmationTokenRepository;
import com.micache.security.jwt.entity.UserEntity;
import com.micache.security.jwt.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConfirmUserUseCaseImplTest {
    @Mock
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Mock
    private UserRepository userRepository;
    private UserMapper userMapper = new UserMapper();

    private ConfirmUserUseCase underTest;
    @BeforeEach
    public void setUp() {
        underTest = new ConfirmUserUseCaseImpl(
                confirmationTokenRepository,
                userRepository,
                userMapper
        );
    }
    @Test
    void givenTokenAndUserIdConfirmationNotFoundThenThrowError() {
        // given
        String token = "token";
        String userId = UUID.randomUUID().toString();
        String expectedError = "Token not found";
        when(confirmationTokenRepository.findByIdUserAndConfirmationToken(any(), anyString()))
                .thenReturn(Optional.empty());
        // when
        try {
            underTest.execute(token, userId);
        } catch (ConfirmationTokenNotFound ex) {
            assertThat(expectedError.equals(ex.getMessage()));
        }
        // then
    }

    @Test
    void givenTokenAndUserIdValidThenActivateUser() {
        // given
        String token = "token";
        String userId = UUID.randomUUID().toString();
        ConfirmationTokenEntity confirmationTokenEntity =
                ConfirmationTokenEntity.builder()
                        .idUser(UUID.fromString(userId))
                        .confirmationToken(token)
                        .build();
        UserEntity userEntity = UserEntity.builder()
                .active(false)
                .id(UUID.randomUUID())
                .build();

        UserEntity userEntityActivated = UserEntity.builder()
                .active(true)
                .id(UUID.randomUUID())
                .build();
        when(confirmationTokenRepository.findByIdUserAndConfirmationToken(any(), anyString()))
                .thenReturn(Optional.of(confirmationTokenEntity));
        when(userRepository.getById(UUID.fromString(userId))).thenReturn(userEntity);
        when(userRepository.saveAndFlush(any())).thenReturn(userEntityActivated);
        // when
        underTest.execute(token, userId);
        // then
        assertTrue(userEntityActivated.isActive());
    }
}