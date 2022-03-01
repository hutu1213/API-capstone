package project.apicapstone.service;

import project.apicapstone.dto.mail.MailDto;
import project.apicapstone.entity.Applicant;

import javax.mail.MessagingException;

public interface MailService {


    void sendEmail(MailDto dto) throws MessagingException;

    void sendEmailRejectApplicant(Applicant applicant);
}
