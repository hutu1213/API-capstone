package project.apicapstone.service.impl;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

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
    @Value("${spring.mail.password}")
    private String password;// = "jwjrsvfazqwbjpcv";
    @Value("${spring.mail.host}")
    private String smtp;// = "smtp.gmail.com";
    @Value("${spring.mail.port}")
    private String port;// = "587";
    private JavaMailSender javaMailSender;

    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void sendEmail(MailDto dto) throws MessagingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtp);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);

        MimeMessage msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(dto.getTo()) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(dto.getSubject(),"UTF-8");
        msg.setText(dto.getBody(),"UTF-8");
        msg.setContent(dto.getBody(), "text/html");

        // sends the e-mail
        Transport.send(msg);


//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setFrom("huytq1899@gmail.com");
//        //message.setFrom(dto.getFrom());
//        message.setTo(dto.getTo());
//        message.setText(dto.getBody());
//        message.setSubject(dto.getSubject());
//
//
//        javaMailSender.send(message);
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
