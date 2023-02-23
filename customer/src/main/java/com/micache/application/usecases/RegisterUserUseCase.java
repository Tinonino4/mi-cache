package com.micache.application.usecases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.micache.infrastructure.adapters.input.rest.model.AuthenticationResponse;
import com.micache.infrastructure.adapters.input.rest.model.RegisterRequest;

public interface RegisterUserUseCase {
    AuthenticationResponse execute(RegisterRequest authenticationRequest) throws JsonProcessingException;
}
