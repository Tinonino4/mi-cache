package com.micache.domain.ports.input;


import com.micache.infrastructure.adapters.input.rest.model.AuthenticationRequest;
import com.micache.infrastructure.adapters.input.rest.model.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

public interface AuthUser {
    ResponseEntity<AuthenticationResponse> authUser(AuthenticationRequest request);
}
