package project.apicapstone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.entity.Notification;
import project.apicapstone.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/v1/api/notification")
public class NotificationController {
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/get-by-accountId/{id}")
    public Object getAllByAccountId(@PathVariable String id) {
        List<Notification> notificationList = notificationService.getAllByAccountId(id);
        return ResponseHandler.getResponse(notificationList, HttpStatus.OK);
    }

}
