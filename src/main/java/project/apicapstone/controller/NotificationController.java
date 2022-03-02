package project.apicapstone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.entity.Notification;
import project.apicapstone.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
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
