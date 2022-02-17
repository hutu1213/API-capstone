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

    @PostMapping("/send-mail")
    public Object sendMail(@RequestParam("to") String to, @RequestParam("body") String body, @RequestParam("subject") String subject) {
        mailService.sendEmail(to, body, subject);
        return ResponseHandler.getResponse(HttpStatus.OK);
    }
    @PostMapping()
    public Object sendMail2(MailDto dto) {
        mailService.sendEmail(dto);
        return ResponseHandler.getResponse(HttpStatus.OK);
    }
}
