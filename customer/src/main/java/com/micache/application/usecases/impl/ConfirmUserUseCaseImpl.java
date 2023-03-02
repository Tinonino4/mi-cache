package com.micache.application.usecases.impl;

import com.micache.application.usecases.ConfirmUserUseCase;
import com.micache.domain.exception.ConfirmationTokenNotFound;
import com.micache.domain.model.User;
import com.micache.infrastructure.adapters.UserRepository;
import com.micache.infrastructure.adapters.output.repository.entity.ConfirmationTokenEntity;
import com.micache.infrastructure.adapters.output.repository.entity.ConfirmationTokenRepository;
import com.micache.security.jwt.entity.UserEntity;
import com.micache.security.jwt.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConfirmUserUseCaseImpl implements ConfirmUserUseCase {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public String execute(String token, String userId) {
        checkConfirmationToken(token, userId);
        UserEntity userEntity = userRepository.getById(UUID.fromString(userId));
        User user = mapper.toUserFromUserEntity(userEntity);
        user.activate();
        return userRepository.saveAndFlush(mapper.toUserEntityFromUser(user)).getId().toString();
    }

    private void checkConfirmationToken(String token, String userId) {
        Optional<ConfirmationTokenEntity> confirmationToken =
                confirmationTokenRepository
                        .findByIdUserAndConfirmationToken(
                                UUID.fromString(userId),
                                token
                        );
        if (confirmationToken.isEmpty()) {
            throw new ConfirmationTokenNotFound("Token not found");
        }
    }
}
