package br.com.igrejaidef.Idef.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String body, String topic) throws MessagingException {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setFrom("jonathan.lauro.guitar@gmail.com");
            helper.setSubject(topic);
            helper.setTo(to);
            helper.setText(body, true);
            javaMailSender.send(message);
        }catch (MessagingException exception) {
            throw new MessagingException("Erro.");
        }
    }
}
