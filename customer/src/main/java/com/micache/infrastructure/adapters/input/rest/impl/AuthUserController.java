package com.micache.infrastructure.adapters.input.rest.impl;

import com.micache.application.usecases.AuthUserUseCase;
import com.micache.infrastructure.adapters.input.rest.AuthUser;
import com.micache.infrastructure.adapters.input.rest.model.AuthenticationRequest;
import com.micache.infrastructure.adapters.input.rest.model.AuthenticationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
public class AuthUserController implements AuthUser {
    private final AuthUserUseCase authUserUseCase;

    public AuthUserController(AuthUserUseCase authUserUseCase) {
        this.authUserUseCase = authUserUseCase;
    }

    @Override
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authUser(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authUserUseCase.execute(request));
    }
}
