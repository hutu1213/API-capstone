package project.apicapstone.service;

import org.springframework.http.HttpRequest;
import project.apicapstone.entity.Employee;
import project.apicapstone.entity.Notification;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface NotificationService {


    List<Notification> getAllByAccountId(String id);
}
