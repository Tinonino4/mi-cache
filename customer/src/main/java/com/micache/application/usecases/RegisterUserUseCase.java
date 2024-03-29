package com.micache.application.usecases;

import com.micache.domain.exception.UserAlreadyExistsException;
import com.micache.infrastructure.adapters.input.rest.model.RegisterRequest;
import com.micache.infrastructure.adapters.input.rest.model.RegisterResponse;

import java.io.IOException;

public interface RegisterUserUseCase {
    RegisterResponse execute(RegisterRequest authenticationRequest) throws IOException, UserAlreadyExistsException;
}
