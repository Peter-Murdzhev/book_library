package com.example.book_library.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements EmailSender{
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String message) {
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
            helper.setText(message);
            helper.setTo(to);
            helper.setSubject("Date of returning book expired!");
            helper.setFrom("stankova.sonya@gmail.com");

            mailSender.send(mimeMessage);
        }catch (MessagingException me){
            LOGGER.error("failed to send email", me);
            throw new IllegalStateException("Failed to send email");
        }
    }
}
