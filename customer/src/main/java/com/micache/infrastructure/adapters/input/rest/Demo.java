package com.micache.infrastructure.adapters.input.rest;


import com.micache.infrastructure.adapters.input.rest.model.AuthenticationRequest;
import com.micache.infrastructure.adapters.input.rest.model.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

public interface Demo {
    ResponseEntity<AuthenticationResponse> authUser(AuthenticationRequest request);
}
