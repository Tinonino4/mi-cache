package com.micache.application.usecases;

import com.micache.infrastructure.adapters.input.rest.model.ConfirmResponse;

import java.util.UUID;

public interface ConfirmUserUseCase {
    ConfirmResponse execute(UUID token, UUID userId);
}
