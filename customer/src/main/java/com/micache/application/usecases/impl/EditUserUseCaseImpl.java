package com.micache.application.usecases.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.micache.application.usecases.EditUserUseCase;
import com.micache.domain.exception.UserAlreadyExistsException;
import com.micache.domain.model.User;
import com.micache.infrastructure.adapters.input.rest.model.RegisterRequest;
import com.micache.infrastructure.adapters.input.rest.model.RegisterResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EditUserUseCaseImpl implements EditUserUseCase {

    private final User user;
    @Override
    public RegisterResponse execute(RegisterRequest authenticationRequest) throws JsonProcessingException, UserAlreadyExistsException {
        return null;
    }
}
