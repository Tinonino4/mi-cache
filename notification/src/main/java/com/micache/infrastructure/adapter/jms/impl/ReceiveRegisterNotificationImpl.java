package com.micache.infrastructure.adapter.jms.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micache.application.usecases.SendRegisterConfirmationUseCase;
import com.micache.infrastructure.adapter.jms.ReceiveRegisterNotification;
import com.micache.infrastructure.adapter.jms.model.NotificationRequest;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReceiveRegisterNotificationImpl implements ReceiveRegisterNotification {
    private final SendRegisterConfirmationUseCase sendRegisterConfirmationUseCase;
    private final ObjectMapper mapper;
    @Override
    @SqsListener("notification-queue")
    public void receiveNotification(String notificationRequest) throws JsonProcessingException {
            log.info("Received message: {}", notificationRequest);
            NotificationRequest notification = mapper.readValue(notificationRequest, NotificationRequest.class);
            sendRegisterConfirmationUseCase.execute(notificationRequest);
    }
}
