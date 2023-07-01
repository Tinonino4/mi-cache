package com.micache.application.usecases.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.micache.application.usecases.RegisterUserUseCase;
import com.micache.application.usecases.SendRegisterConfirmationUseCase;
import com.micache.application.usecases.mapper.ConfirmationTokenMapper;
import com.micache.domain.exception.UserAlreadyExistsException;
import com.micache.domain.model.Role;
import com.micache.domain.model.User;
import com.micache.infrastructure.adapters.input.rest.model.NotificationRequest;
import com.micache.infrastructure.adapters.input.rest.model.NotificationType;
import com.micache.infrastructure.adapters.input.rest.model.RegisterResponse;
import com.micache.infrastructure.adapters.output.repository.UserRepository;
import com.micache.infrastructure.adapters.input.rest.model.RegisterRequest;
import com.micache.infrastructure.adapters.output.repository.ConfirmationTokenRepository;
import com.micache.security.jwt.entity.UserEntity;
import com.micache.security.jwt.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import com.micache.domain.model.ConfirmationToken;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {
    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final ConfirmationTokenMapper confirmationTokenMapper;
    private final SendRegisterConfirmationUseCase sendRegisterConfirmationUseCase;
    @Override
    public RegisterResponse execute(RegisterRequest request) throws IOException {
        log.info("{} - add User: {}", RequestMethod.POST, request.toString());
        userMapper.toUserFromRegisterRequest(request).isEmailValid();

        Optional<UserEntity> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists: " + request.getEmail());
        }

        var user = buildUser(request);
        UserEntity userSaved = userRepository.saveAndFlush(userMapper.toUserEntityFromUser(user));

        ConfirmationToken confirmationTokenSaved =
                createAndSaveConfirmationToken(userSaved.getId());

        NotificationRequest notificationRequest = NotificationRequest.builder()
                .id(userSaved.getId())
                .firstname(userSaved.getFirstname())
                .lastname(userSaved.getLastname())
                .email(userSaved.getEmail())
                .token(confirmationTokenSaved.getConfirmationToken())
                .notificationType(NotificationType.WELCOME)
                .build();
        sendRegisterConfirmationUseCase.execute(notificationRequest);

        return RegisterResponse.builder()
                .token(confirmationTokenSaved.getConfirmationToken())
                .userId(userSaved.getId())
                .build();
    }

    private ConfirmationToken createAndSaveConfirmationToken(UUID id) {
        ConfirmationToken confirmationToken = new ConfirmationToken(id);
        return confirmationTokenMapper.toConfirmationToken(
                confirmationTokenRepository.saveAndFlush(
                        confirmationTokenMapper.toConfirmationTokenEntity(confirmationToken)
                )
        );
    }

    private User buildUser(RegisterRequest request) {
        return User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CACHER)
                .created_at(new Date())
                .build();
    }
}
