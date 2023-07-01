package com.micache.domain.ports.input;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.micache.infrastructure.adapters.input.rest.model.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface EditUser {
    ResponseEntity<?> edit(RegisterRequest request) throws JsonProcessingException;
}
