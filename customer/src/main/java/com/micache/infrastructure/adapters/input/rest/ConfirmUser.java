package com.micache.infrastructure.adapters.input.rest;

import org.springframework.http.ResponseEntity;

public interface ConfirmUser {
    ResponseEntity<String> activateUser(String token, String userId);
}
