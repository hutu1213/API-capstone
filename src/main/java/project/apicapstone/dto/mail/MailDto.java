package project.apicapstone.dto.mail;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailDto {
    //private String from;
    private String to;
    private String body;
    private String subject;
}
