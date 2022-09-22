package project.apicapstone.service;

import project.apicapstone.entity.RequestNotification;

import java.util.List;

public interface RequestNotificationService {
    List<RequestNotification> getAllByAccountId(String id);
}
