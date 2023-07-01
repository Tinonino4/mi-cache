package com.micache.application.usecases.impl;

import com.micache.application.usecases.ConfirmUserUseCase;
import com.micache.application.usecases.mapper.UserSkillsValuesMapper;
import com.micache.domain.exception.ConfirmationTokenNotFound;
import com.micache.domain.model.User;
import com.micache.domain.model.UserSkillsValues;
import com.micache.infrastructure.adapters.input.rest.model.ConfirmResponse;
import com.micache.infrastructure.adapters.output.repository.UserRepository;
import com.micache.infrastructure.adapters.output.repository.UserSkillsValuesRepository;
import com.micache.infrastructure.adapters.output.repository.entity.ConfirmationTokenEntity;
import com.micache.infrastructure.adapters.output.repository.ConfirmationTokenRepository;
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
    private final UserSkillsValuesRepository userSkillsValuesRepository;
    private final UserSkillsValuesMapper userSkillsValuesMapper;

    @Override
    public ConfirmResponse execute(UUID token, UUID userId) {
        checkConfirmationToken(token, userId);
        UserEntity userEntity = userRepository.getById(userId);
        User user = mapper.toUserFromUserEntity(userEntity);
        user.activate();
        userSkillsValuesRepository.saveAndFlush(
                userSkillsValuesMapper.toUserSkillsValuesEntity(
                        new UserSkillsValues(user.getId())
                )
        );
        return new ConfirmResponse(
                userRepository.saveAndFlush(
                        mapper.toUserEntityFromUser(user)
                ).getId()
        );
    }
    private void checkConfirmationToken(UUID token, UUID userId) {
        Optional<ConfirmationTokenEntity> confirmationToken =
                confirmationTokenRepository
                        .findByIdUserAndConfirmationToken(
                                userId,
                                token
                        );
        if (confirmationToken.isEmpty()) {
            throw new ConfirmationTokenNotFound("Token not found");
        }
    }
}
