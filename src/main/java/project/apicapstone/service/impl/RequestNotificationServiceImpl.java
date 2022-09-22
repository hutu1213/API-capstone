package project.apicapstone.service.impl;

import org.springframework.stereotype.Service;
import project.apicapstone.entity.RequestNotification;
import project.apicapstone.repository.RequestNotificationRepository;
import project.apicapstone.service.RequestNotificationService;

import java.util.List;

@Service
public class RequestNotificationServiceImpl implements RequestNotificationService {
    private final RequestNotificationRepository requestNotificationRepository;

    public RequestNotificationServiceImpl(RequestNotificationRepository requestNotificationRepository) {
        this.requestNotificationRepository = requestNotificationRepository;
    }

    @Override
    public List<RequestNotification> getAllByAccountId(String id) {
        return requestNotificationRepository.getRequestNotificationByAccountId(id);
    }
}
