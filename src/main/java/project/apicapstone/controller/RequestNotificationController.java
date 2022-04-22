package project.apicapstone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.entity.Notification;
import project.apicapstone.entity.RequestNotification;
import project.apicapstone.service.RequestNotificationService;

import java.util.List;

@RestController
@RequestMapping("/v1/api/request-notification")
public class RequestNotificationController {
    private final RequestNotificationService requestNotificationService;

    public RequestNotificationController(RequestNotificationService requestNotificationService) {
        this.requestNotificationService = requestNotificationService;
    }
    @GetMapping("/get-by-accountId/{id}")
    public Object getAllByAccountId(@PathVariable String id) {
        List<RequestNotification> notificationList = requestNotificationService.getAllByAccountId(id);
        return ResponseHandler.getResponse(notificationList, HttpStatus.OK);
    }
}
