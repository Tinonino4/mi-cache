package com.micache.application.usecases.impl;

import com.micache.application.usecases.SendRegisterConfirmationUseCase;
import com.micache.domain.ConfirmationToken;
import com.micache.infrastructure.adapter.jms.model.NotificationRequest;
import com.micache.infrastructure.adapter.output.repository.ConfirmationTokenRepository;
import com.micache.infrastructure.adapter.output.repository.mapper.ConfirmationTokenMapper;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendRegisterConfirmationUseCaseImpl implements SendRegisterConfirmationUseCase {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final ConfirmationTokenMapper mapper;
    @Value("${notification.sender}")
    private String sender;
    @Value("${notification.subject}")
    private String subject;
    @Value("${notification.template}")
    private String template;
    @Value("${notification.url.base}")
    private String urlBase;
    @Value("${notification.url.endpoint}")
    private String endpoint;
    @Value("${sendgrid.api-key}")
    private String sendgridApiKey;
    @Value("${sendgrid.endpoint}")
    private String sendgridEndpoint;

    @Override
    public void execute(NotificationRequest notificationRequest) throws IOException {
        sendMail(notificationRequest);
    }

    private void sendMail(NotificationRequest notificationRequest) throws IOException {
        Email from = new Email(sender);
        Email to = new Email(notificationRequest.getEmail());
        Content content = new Content("text/html", "I'm replacing the <strong>body tag</strong>");
        Mail mail = new Mail(from, subject, to, content);

        mail.personalization.get(0).addDynamicTemplateData("USER_NAME", notificationRequest.getFirstname());
        mail.personalization.get(0).addDynamicTemplateData("USER_SURNAME", notificationRequest.getLastname());
        mail.personalization.get(0).addDynamicTemplateData("LINK", urlBase + endpoint + notificationRequest.getToken());
        mail.setTemplateId(template);

        SendGrid sg = new SendGrid(sendgridApiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint(sendgridEndpoint);
            request.setBody(mail.build());
            sg.api(request);
        } catch (IOException ex) {
            throw ex;
        }
    }
}
