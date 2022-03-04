package project.apicapstone.service.impl;

import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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


    public NotificationServiceImpl(EmployeeRepository employeeRepository,NotificationRepository notificationRepository, AccountService accountService, JwtUtils jwtUtils) {
        this.notificationRepository = notificationRepository;
        this.jwtUtils = jwtUtils;
        this.employeeRepository=employeeRepository;
        this.accountService = accountService;
    }

    @Override
    public List<Notification> setNotitfi(List<Employee> employeeList, HttpServletRequest request) {
        List<Notification> notificationList = new ArrayList<>();
        // option2: làm như job
        // sai or đúng: mỗi lần chạy là mỗi lần set id mới
        //  tạo 1 biến trong employee + nó trong hàm checkBirth -> sau năm khác thì set lại 0
        // *********sau khi lưu noti -> get notifi by account
        for (int i = 0; i < employeeList.size(); i++) {
            Notification notification = new Notification();
            notification.setCreateDate(LocalDate.now());
            notification.setContent("Hôm nay là sinh nhật của " + employeeList.get(i).getEmployeeName() + ", mã: " + employeeList.get(i).getEmployeeId());
            //****************set notification vào 2 account có role là admin và quản lí ns***********
            // lấy username sau khi đăng nhập
            String username = jwtUtils.getUsernameFromToken(jwtUtils.getJwtTokenFromRequest(request));
            Account account = accountService.findByUsername(username);
            notification.setAccount(account);


            notificationRepository.save(notification);
            notificationList.add(notification);
        }

        return notificationList;
    }

    @Override
    public List<Notification> getAllByAccountId(String id) {
        return notificationRepository.getNotificationsByAccount_AccountId(id);
    }
}
