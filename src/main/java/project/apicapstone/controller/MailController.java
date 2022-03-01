package project.apicapstone.controller;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.mail.MailDto;
import project.apicapstone.service.MailService;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/mail")
public class MailController {
    private MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @SneakyThrows
    @PostMapping("/send")
    public Object sendMail(@RequestBody MailDto dto) throws MessagingException {
        try {
            mailService.sendEmail(dto);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return ResponseHandler.getResponse(HttpStatus.OK);
    }
}
