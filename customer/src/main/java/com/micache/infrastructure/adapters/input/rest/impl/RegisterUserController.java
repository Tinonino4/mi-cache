package com.micache.infrastructure.adapters.input.rest.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.micache.application.usecases.RegisterUserUseCase;
import com.micache.domain.exception.InvalidEmailException;
import com.micache.domain.exception.UserAlreadyExistsException;
import com.micache.domain.ports.input.RegisterUser;
import com.micache.infrastructure.adapters.input.rest.model.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/register")
@RequiredArgsConstructor
public class RegisterUserController implements RegisterUser {
    private final RegisterUserUseCase registerUser;

    @Override
    @PostMapping()
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) throws JsonProcessingException {
        return ResponseEntity.ok(registerUser.execute(request));
    }
}
