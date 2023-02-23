package com.micache.application.usecases;


import com.micache.infrastructure.adapters.input.rest.model.AuthenticationRequest;
import com.micache.infrastructure.adapters.input.rest.model.AuthenticationResponse;

public interface AuthUserUseCase {

    AuthenticationResponse execute(AuthenticationRequest authenticationRequest);
}
