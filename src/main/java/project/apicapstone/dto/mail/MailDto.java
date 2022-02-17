package project.apicapstone.dto.mail;

import lombok.Data;

@Data
public class MailDto {
    //private String from;
    private String to;
    private String body;
    private String subject;
}
