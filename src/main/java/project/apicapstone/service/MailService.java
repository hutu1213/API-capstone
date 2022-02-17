package project.apicapstone.service;

import project.apicapstone.dto.mail.MailDto;
import project.apicapstone.entity.Applicant;

public interface MailService {
    void sendEmail(String to, String body, String subject);

    void sendEmail(MailDto dto);

    void sendEmailRejectApplicant(Applicant applicant);
}
