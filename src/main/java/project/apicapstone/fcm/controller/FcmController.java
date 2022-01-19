package project.apicapstone.fcm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.apicapstone.fcm.dto.NotificationRequestDto;
import project.apicapstone.fcm.dto.SubscriptionRequestDto;
import project.apicapstone.fcm.dto.TopicRequestDto;
import project.apicapstone.fcm.service.PushNotificationService;

@RestController
@RequestMapping("/api/fcm")
public class FcmController {
    private PushNotificationService pushNotificationService;

    public FcmController(PushNotificationService pushNotificationService) {
        this.pushNotificationService = pushNotificationService;
    }
    @PostMapping("/token")
    public String sendPnsToDevice(@RequestBody NotificationRequestDto notificationRequestDto) {
        return pushNotificationService.sendPnsToDevice(notificationRequestDto);
    }
    @PostMapping("/subscribe")
    public void subscribeToTopic(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        pushNotificationService.subscribeToTopic(subscriptionRequestDto);
    }
    @PostMapping("/topic")
    public String sendPnsToTopic(@RequestBody TopicRequestDto notificationRequestDto) {
        return pushNotificationService.sendPnsToTopic(notificationRequestDto);
    }
}
