package com.flabum.squidzbackend.iam.infrastructure.mail;

import jakarta.mail.MessagingException;

public interface MailService {

    public void sendEmail(String toEmail, String subject, String body) throws MessagingException;
}
