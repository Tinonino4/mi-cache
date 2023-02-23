package com.micache.infrastructure.adapters.jms.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micache.infrastructure.adapters.jms.RegisterNotification;
import com.micache.infrastructure.adapters.jms.mapper.NotificationRequestMapper;
import com.micache.infrastructure.adapters.jms.model.NotificationRequest;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Lazy
public class RegisterNotificationImpl implements RegisterNotification {

    private final QueueMessagingTemplate queueMessagingTemplate;
    @Value("${cloud.aws.end-point.uri}")
    private String endPoint;
    private final NotificationRequestMapper mapper;
    private final String QUEUE_NAME = "notifications-queue";
    private final ObjectMapper objectMapper;
    @Override
    public void sendNotification(NotificationRequest notificationRequest) throws JsonProcessingException {
        log.info("sendNotification " + notificationRequest);
        queueMessagingTemplate.send(endPoint, MessageBuilder.withPayload(
                objectMapper.writeValueAsString(notificationRequest))
                .build());
    }
}
