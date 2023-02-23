package com.micache.infrastructure.adapters.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.micache.infrastructure.adapters.jms.model.NotificationRequest;

public interface RegisterNotification {

    void sendNotification(NotificationRequest notificationRequest) throws JsonProcessingException;
}
