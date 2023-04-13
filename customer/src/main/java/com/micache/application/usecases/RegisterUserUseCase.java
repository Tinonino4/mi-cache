package com.micache.application.usecases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.micache.domain.exception.UserAlreadyExistsException;
import com.micache.infrastructure.adapters.input.rest.model.RegisterRequest;
import com.micache.infrastructure.adapters.input.rest.model.RegisterResponse;

public interface RegisterUserUseCase {
    RegisterResponse execute(RegisterRequest authenticationRequest) throws JsonProcessingException, UserAlreadyExistsException;
}
