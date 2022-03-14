package project.apicapstone.firebase.controller;

import com.google.firebase.messaging.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.apicapstone.firebase.dto.*;
import project.apicapstone.firebase.service.FCMService;
import project.apicapstone.firebase.service.PushNotificationService;

import java.util.concurrent.ExecutionException;

@RestController
public class PushNotificationController {
    private PushNotificationService pushNotificationService;
    private FCMService fcmService;

    public PushNotificationController(PushNotificationService pushNotificationService, FCMService fcmService) {
        this.pushNotificationService = pushNotificationService;
        this.fcmService = fcmService;
    }

//    // send Push notification to device token ~~ /notification/token
//    @PostMapping("/token")
//    public String sendPnsToDevice(@RequestBody NotificationRequestDto notificationRequestDto) {
//        return pushNotificationService.sendPnsToDevice(notificationRequestDto);
//    }
//
//    //    @PostMapping("/subscribe")
////    public String subscribeToTopic(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
////        pushNotificationService.subscribeToTopic(subscriptionRequestDto);
////    }
//    @PostMapping("/subscribe")
//    public void subscribeToTopic(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
//        pushNotificationService.subscribeToTopic(subscriptionRequestDto);
//    }
//
//    @PostMapping("/unsubscribe")
//    public void unsubscribeFromTopic(SubscriptionRequestDto subscriptionRequestDto) {
//        pushNotificationService.unsubscribeFromTopic(subscriptionRequestDto);
//    }
//
//    @PostMapping("/topic")
//    public String sendPnsToTopic(@RequestBody TopicRequestDto notificationRequestDto) {
//        return pushNotificationService.sendPnsToTopic(notificationRequestDto);
//    }

//    @PostMapping("/notification/token")
//    public ResponseEntity sendTokenNotification(@RequestBody PushNotificationRequest request) throws ExecutionException, InterruptedException {
//        // pushNotificationService.sendPushNotificationToToken(request);
//        String json = fcmService.sendMessageToToken(request);
//        //return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
//        return ResponseEntity.ok(json);
//    }
}
