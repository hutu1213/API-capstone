package project.apicapstone.service.impl;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.mail.MailDto;
import project.apicapstone.entity.Applicant;
import project.apicapstone.service.MailService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeMessage;

import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {
    @Value("${spring.mail.username}")
    private String userName ;//= "huytq1899@gmail.com";

    private JavaMailSender javaMailSender;

    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void sendEmail(MailDto dto) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(dto.getBody(), true);
        helper.setTo(dto.getTo());
        helper.setSubject(dto.getSubject());
        helper.setFrom(userName);
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendEmailRejectApplicant(Applicant applicant) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("huytq1899@gmail.com");
        //message.setFrom(dto.getFrom());
        message.setTo(applicant.getEmail());
        message.setText("Bạn không vượt qua phỏng vấn");
        message.setSubject("Thông báo phỏng vấn");

        javaMailSender.send(message);
    }
}
