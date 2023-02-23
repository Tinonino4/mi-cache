package com.micache.infrastructure.adapters.jms.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.micache.infrastructure.adapters.jms.model.NotificationRequest;
import org.springframework.stereotype.Component;

@Component
public class NotificationRequestMapper {
    public String toJson(NotificationRequest notificationRequest) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(notificationRequest);
    }
}
