package project.apicapstone.firebase.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.apicapstone.firebase.dto.NotificationRequestDto;
import project.apicapstone.firebase.dto.SubscriptionRequestDto;
import project.apicapstone.firebase.dto.TopicRequestDto;
import project.apicapstone.firebase.service.PushNotificationService;

@RestController
public class PushNotificationController {
    private PushNotificationService pushNotificationService;

    public PushNotificationController(PushNotificationService pushNotificationService) {
        this.pushNotificationService = pushNotificationService;
    }

    // send Push notification to device token ~~ /notification/token
    @PostMapping("/token")
    public String sendPnsToDevice(@RequestBody NotificationRequestDto notificationRequestDto) {
        return pushNotificationService.sendPnsToDevice(notificationRequestDto);
    }

    //    @PostMapping("/subscribe")
//    public String subscribeToTopic(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
//        pushNotificationService.subscribeToTopic(subscriptionRequestDto);
//    }
    @PostMapping("/subscribe")
    public void subscribeToTopic(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        pushNotificationService.subscribeToTopic(subscriptionRequestDto);
    }

    @PostMapping("/unsubscribe")
    public void unsubscribeFromTopic(SubscriptionRequestDto subscriptionRequestDto) {
        pushNotificationService.unsubscribeFromTopic(subscriptionRequestDto);
    }

    @PostMapping("/topic")
    public String sendPnsToTopic(@RequestBody TopicRequestDto notificationRequestDto) {
        return pushNotificationService.sendPnsToTopic(notificationRequestDto);
    }
}
