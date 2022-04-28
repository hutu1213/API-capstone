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
    private String userName;//= "huytq1899@gmail.com";

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
    public void sendEmailRejectApplicant(Applicant applicant) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText("Gửi " + "<b>" + applicant.getApplicantName() + "</b>" + ",<br/><br/>" +
                "Cảm ơn vì sự quan tâm của bạn đối với công ty Lug và vị trí " + "<b>" + applicant.getJobPosting().getTitle().getJobTitle() + "</b>" + ". Sau khi xem xét các hồ sơ nhận được, chúng tôi rất tiếc không thể chọn bạn đi tiếp. <br/><br/>" +
                "Ban tuyển dụng đánh giá cao thời gian bạn dành để ứng tuyển. Chúc bạn may mắn trong quá trình tìm việc và mong rằng có thể hợp tác với bạn ở những vị trí việc làm khác trong tương lai.<br/><br/>" +
                "Trân trọng!", true);
        helper.setTo(applicant.getEmail());
        helper.setSubject("Thông báo kết quả");
        helper.setFrom(userName);
        javaMailSender.send(mimeMessage);
    }
}
