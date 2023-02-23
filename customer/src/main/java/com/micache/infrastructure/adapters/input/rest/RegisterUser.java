package com.micache.infrastructure.adapters.input.rest;

import com.micache.infrastructure.adapters.input.rest.model.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface RegisterUser {
    ResponseEntity<?> register(RegisterRequest request);
}
