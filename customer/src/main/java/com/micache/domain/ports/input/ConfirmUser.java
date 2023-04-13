package com.micache.domain.ports.input;

import com.micache.infrastructure.adapters.input.rest.model.ConfirmResponse;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface ConfirmUser {
    ResponseEntity<ConfirmResponse> activateUser(UUID token, UUID userId);
}
