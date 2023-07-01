package com.micache.application.usecases;



import com.micache.infrastructure.adapters.input.rest.model.NotificationRequest;

import java.io.IOException;

public interface SendRegisterConfirmationUseCase {
    void execute(NotificationRequest notificationRequest) throws IOException;
}
