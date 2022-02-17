package project.apicapstone.firebase.dto;

import lombok.Data;

@Data
public class PushNotificationRequest {
    private String title;
    private String message;
    private String topic;
    private String token;

    public PushNotificationRequest(String title, String message, String topic) {
        this.title=title;
        this.message=message;
        this.topic=topic;
    }
}
