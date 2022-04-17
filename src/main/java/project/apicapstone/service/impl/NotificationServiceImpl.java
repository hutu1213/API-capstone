package project.apicapstone.service.impl;

import org.springframework.stereotype.Service;
import project.apicapstone.entity.Account;
import project.apicapstone.entity.Employee;
import project.apicapstone.entity.Notification;
import project.apicapstone.repository.EmployeeRepository;
import project.apicapstone.repository.NotificationRepository;
import project.apicapstone.sercurity.jwt.JwtUtils;
import project.apicapstone.service.AccountService;
import project.apicapstone.service.NotificationService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    private NotificationRepository notificationRepository;
    private AccountService accountService;
    private JwtUtils jwtUtils;
    private EmployeeRepository employeeRepository;


    public NotificationServiceImpl(EmployeeRepository employeeRepository, NotificationRepository notificationRepository, AccountService accountService, JwtUtils jwtUtils) {
        this.notificationRepository = notificationRepository;
        this.jwtUtils = jwtUtils;
        this.employeeRepository = employeeRepository;
        this.accountService = accountService;
    }


    @Override
    public List<Notification> getAllByAccountId(String id) {
        //return notificationRepository.getNotificationsByAccount_AccountId(id);
        return notificationRepository.getNotificationsByAccountId(id);
    }
}
