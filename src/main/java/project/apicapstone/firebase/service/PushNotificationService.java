package project.apicapstone.firebase.service;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import project.apicapstone.firebase.dto.NotificationRequestDto;
import project.apicapstone.firebase.dto.PushNotificationRequest;
import project.apicapstone.firebase.dto.SubscriptionRequestDto;
import project.apicapstone.firebase.dto.TopicRequestDto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
@Slf4j
@Service
public class PushNotificationService {


//    private FirebaseApp firebaseApp;
//    private Logger logger = LoggerFactory.getLogger(PushNotificationService.class);
//    private FCMService fcmService;
//
//    public PushNotificationService(FCMService fcmService) {
//        this.fcmService=fcmService;
//    }
//
//
//
////    public void sendPushNotification(PushNotificationRequest request) {
////        try {
////            fcmService.sendMessage(getSamplePayloadData(), request);
////        } catch (InterruptedException | ExecutionException e) {
////            logger.error(e.getMessage());
////        }
////    }
//
////    public void sendPushNotificationWithoutData(PushNotificationRequest request) {
////        try {
////            fcmService.sendMessageWithoutData(request);
////        } catch (InterruptedException | ExecutionException e) {
////            logger.error(e.getMessage());
////        }
////    }
////
////
////    public Message sendPushNotificationToToken(PushNotificationRequest request) {
////        try {
////            return fcmService.sendMessageToToken(request);
////        } catch (InterruptedException | ExecutionException e) {
////            logger.error(e.getMessage());
////        }
////    }
//
//
////    private Map<String, String> getSamplePayloadData() {
////        Map<String, String> pushData = new HashMap<>();
////        pushData.put("messageId", defaults.get("payloadMessageId"));
////        pushData.put("text", defaults.get("payloadData") + " " + LocalDateTime.now());
////        return pushData;
////    }
////
////
////    private PushNotificationRequest getSamplePushNotificationRequest() {
////        PushNotificationRequest request = new PushNotificationRequest(defaults.get("title"),
////                defaults.get("message"),
////                defaults.get("topic"));
////        return request;
////    }
//
//    public String sendPnsToDevice(NotificationRequestDto notificationRequestDto) {
//        Message message = Message.builder()
//                .setToken(notificationRequestDto.getToken())
//                .setNotification(new Notification(notificationRequestDto.getTitle(), notificationRequestDto.getBody()))
//                .putData("content", notificationRequestDto.getTitle())
//                .putData("body", notificationRequestDto.getBody())
//                .build();
//
//        String response = null;
//        try {
//            response = FirebaseMessaging.getInstance().send(message);
//        } catch (FirebaseMessagingException e) {
//            org.apache.logging.log4j.Logger log = null;
//            log.error("Fail to send firebase notification", e);
//        }
//
//        return response;
//    }
//
//    public String sendPnsToTopic(TopicRequestDto notificationRequestDto) {
//        Message message = Message.builder()
//                .setTopic(notificationRequestDto.getTopicName())
//                .setNotification(new Notification(notificationRequestDto.getTitle(), notificationRequestDto.getBody()))
//                .putData("content", notificationRequestDto.getTitle())
//                .putData("body", notificationRequestDto.getBody())
//                .build();
//
//        String response = null;
//        try {
//            FirebaseMessaging.getInstance().send(message);
//        } catch (FirebaseMessagingException e) {
//
//            log.error("Fail to send firebase notification", e);
//        }
//
//        return response;
//    }
//    public void subscribeToTopic(SubscriptionRequestDto subscriptionRequestDto) {
//        try {
//            FirebaseMessaging.getInstance().subscribeToTopic(subscriptionRequestDto.getTokens(),
//                    subscriptionRequestDto.getTopicName());
//
//        } catch (FirebaseMessagingException e) {
//
//            log.error("Firebase subscribe to topic fail", e);
//        }
//
//
//    }
//
//    public void unsubscribeFromTopic(SubscriptionRequestDto subscriptionRequestDto) {
//        try {
//            FirebaseMessaging.getInstance().unsubscribeFromTopic(subscriptionRequestDto.getTokens(),
//                    subscriptionRequestDto.getTopicName());
//        } catch (FirebaseMessagingException e) {
//
//            log.error("Firebase unsubscribe from topic fail", e);
//        }
//    }
//    //send with data
////    public String sendDataPlusNotification(NotificationPlusDataDTO notificationPlusDataDTO){
////        Message message= Message.builder()
////                .setToken(notificationPlusDataDTO.getToken())
////                .setNotification(new com.example.demo.dto.Notification(notificationPlusDataDTO.getNotification().getTitle(), notificationPlusDataDTO.getData().getBody()))
////                .putData("title",notificationPlusDataDTO.getData().getTitle())
////                .putData("body",notificationPlusDataDTO.getData().getBody())
////                .putData("click_action",notificationPlusDataDTO.getData().getClick_action())
////                .build();
////        String response = null;
////        try {
////            response = FirebaseMessaging.getInstance().send(message);
////        } catch (FirebaseMessagingException e) {
////            System.out.println("Fail to send firebase notification"+ e);
////        }
////        return response;
////
////
////    }
}
