package com.micache.application.usecases.impl;

import com.micache.application.usecases.SendRegisterConfirmationUseCase;
import com.micache.infrastructure.adapter.jms.model.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendRegisterConfirmationUseCaseImpl implements SendRegisterConfirmationUseCase {
    @Override
    public void execute(String notificationRequest) {
        log.info("HELLO: " + notificationRequest);
    }
}
