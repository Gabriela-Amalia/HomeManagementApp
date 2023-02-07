package com.example.homemanagement.observerPattern;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class EmailObserver implements Observer {
    private static final String SENDGRID_API_KEY = "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";
    private String recipientEmail;

    private String message;

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public void setMessage(String message) { this.message = message; }

    @Override
    public void update() {
        Email from = new Email("personalAppTesting@gmail.com");
        Email toEmail = new Email(recipientEmail);
        String subject = "You were assigned to a task";
        Content emailContent = new Content("text/plain", message);
        Mail mail = new Mail(from, subject, toEmail, emailContent);

        SendGrid sg = new SendGrid(SENDGRID_API_KEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(recipientEmail);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
