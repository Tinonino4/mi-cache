package com.micache.infrastructure.adapters.input.rest.impl;

import com.micache.application.usecases.ConfirmUserUseCase;
import com.micache.domain.ports.input.ConfirmUser;
import com.micache.infrastructure.adapters.input.rest.model.ConfirmResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/api/v1/confirm")
@RequiredArgsConstructor
public class ConfirmUserController implements ConfirmUser {
    private final ConfirmUserUseCase confirmUserUseCase;
    @Override
    @GetMapping("/{token}/{userId}")
    public ResponseEntity<ConfirmResponse> activateUser(
            @PathVariable UUID token,
            @PathVariable UUID userId
    ) {
        return ResponseEntity.ok(confirmUserUseCase.execute(token, userId));
    }
}
