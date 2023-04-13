package com.micache.application.usecases.impl;

import com.micache.application.usecases.ConfirmUserUseCase;
import com.micache.application.usecases.mapper.UserSkillsValuesMapper;
import com.micache.domain.exception.ConfirmationTokenNotFound;
import com.micache.infrastructure.adapters.output.repository.UserRepository;
import com.micache.infrastructure.adapters.output.repository.UserSkillsValuesRepository;
import com.micache.infrastructure.adapters.output.repository.entity.ConfirmationTokenEntity;
import com.micache.infrastructure.adapters.output.repository.ConfirmationTokenRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConfirmUserUseCaseImplTest {
    @Mock
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Mock
    private UserRepository userRepository;
    private UserMapper userMapper = new UserMapper();
    @Mock
    private UserSkillsValuesRepository userSkillsValuesRepository;
    private UserSkillsValuesMapper userSkillsValuesMapper = new UserSkillsValuesMapper();

    private ConfirmUserUseCase underTest;
    @BeforeEach
    public void setUp() {
        underTest = new ConfirmUserUseCaseImpl(
                confirmationTokenRepository,
                userRepository,
                userMapper,
                userSkillsValuesRepository,
                userSkillsValuesMapper
        );
    }
    @Test
    void givenTokenAndUserIdConfirmationNotFoundThenThrowError() {
        // given
        UUID token = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String expectedError = "Token not found";
        when(confirmationTokenRepository.findByIdUserAndConfirmationToken(any(), any()))
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
        UUID token = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        ConfirmationTokenEntity confirmationTokenEntity =
                ConfirmationTokenEntity.builder()
                        .idUser(userId)
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
        when(confirmationTokenRepository.findByIdUserAndConfirmationToken(any(), any()))
                .thenReturn(Optional.of(confirmationTokenEntity));
        when(userRepository.getById(userId)).thenReturn(userEntity);
        when(userRepository.saveAndFlush(any())).thenReturn(userEntityActivated);
        // when
        underTest.execute(token, userId);
        // then
        verify(userSkillsValuesRepository, times(1)).saveAndFlush(any());
        assertTrue(userEntityActivated.isActive());
    }
}