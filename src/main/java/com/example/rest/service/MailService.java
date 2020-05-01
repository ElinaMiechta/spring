package com.example.rest.service;

import com.example.rest.config.EmailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    private final JavaMailSender javaMailSender;

     @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Autowired
    private EmailConfig emailConfig;

    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setHost(emailConfig.getHost());
        mailSenderImpl.setPort(emailConfig.getPort());
        mailSenderImpl.setUsername(emailConfig.getUsername());
        mailSenderImpl.setPassword(emailConfig.getPassword());
        return mailSenderImpl;
    }





    public void sentMail(String to, String subject,
                         String text, boolean isHtmlContent) {
         try {
             MimeMessage mimeMessage = javaMailSender.createMimeMessage();
             MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
             mimeMessageHelper.setTo(to);
             mimeMessageHelper.setSubject(subject);
             mimeMessageHelper.setText(text, isHtmlContent);
             getJavaMailSender().send(mimeMessage);

         }catch (MessagingException e){
             e.printStackTrace();
         }
    }
}
