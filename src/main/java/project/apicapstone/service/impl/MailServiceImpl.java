package project.apicapstone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.mail.MailDto;
import project.apicapstone.entity.Applicant;
import project.apicapstone.service.MailService;

@Service
public class MailServiceImpl implements MailService {

    private JavaMailSender javaMailSender;
    public MailServiceImpl(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }

    @Override
    public void sendEmail(String to, String body, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("huytq1899@gmail.com");
        //message.setFrom(dto.getFrom());
        message.setTo(to);
        message.setText(body);
        message.setSubject(subject);

        javaMailSender.send(message);
    }

    @Override
    public void sendEmail(MailDto dto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("huytq1899@gmail.com");
        //message.setFrom(dto.getFrom());
        message.setTo(dto.getTo());
        message.setText(dto.getBody());
        message.setSubject(dto.getSubject());

        javaMailSender.send(message);
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
