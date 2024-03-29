package com.micache.domain.ports.input;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.micache.infrastructure.adapters.input.rest.model.RegisterRequest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface RegisterUser {
    ResponseEntity<?> register(RegisterRequest request) throws IOException;
}
