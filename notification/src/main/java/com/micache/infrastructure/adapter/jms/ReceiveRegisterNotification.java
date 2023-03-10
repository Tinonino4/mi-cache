package com.micache.infrastructure.adapter.jms;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.micache.infrastructure.adapter.jms.model.NotificationRequest;

import java.io.IOException;

public interface ReceiveRegisterNotification {

    void receiveNotification(String notificationRequest) throws IOException;
}
