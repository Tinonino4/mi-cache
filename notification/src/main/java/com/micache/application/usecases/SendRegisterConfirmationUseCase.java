package com.micache.application.usecases;


import com.micache.infrastructure.adapter.jms.model.NotificationRequest;

public interface SendRegisterConfirmationUseCase {
    void execute(String notificationRequest);
}
