package com.micache.infrastructure.adapters.input.rest.impl;

import com.micache.application.usecases.ConfirmUserUseCase;
import com.micache.infrastructure.adapters.input.rest.ConfirmUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/confirm")
@RequiredArgsConstructor
public class ConfirmUserController implements ConfirmUser {
    private final ConfirmUserUseCase confirmUserUseCase;
    @Override
    @PostMapping("/{token}/{userId}")
    public ResponseEntity<String> activateUser(
            @PathVariable String token,
            @PathVariable String userId
    ) {
        return ResponseEntity.ok(confirmUserUseCase.execute(token, userId));
    }
}
