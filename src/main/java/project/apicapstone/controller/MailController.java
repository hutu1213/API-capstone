package project.apicapstone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.mail.MailDto;
import project.apicapstone.service.MailService;

@RestController
@RequestMapping("/api/mail")
public class MailController {
    private MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/send")
    public Object sendMail(@RequestBody MailDto dto) {
        mailService.sendEmail(dto);
        return ResponseHandler.getResponse(HttpStatus.OK);
    }
}
