package project.apicapstone.service.impl;

import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import project.apicapstone.entity.Account;
import project.apicapstone.entity.Employee;
import project.apicapstone.entity.Notification;
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
    public NotificationServiceImpl(NotificationRepository notificationRepository,AccountService accountService,JwtUtils jwtUtils) {
        this.notificationRepository = notificationRepository;
        this.jwtUtils=jwtUtils;
        this.accountService=accountService;
    }

    @Override
    public List<Notification> setNotitfi(List<Employee> employeeList, HttpServletRequest request) {
        List<Notification> notificationList = new ArrayList<>();

        // sai or đúng: mỗi lần chạy là mỗi lần set id mới
        // option2: làm như job
        for (int i = 0; i < employeeList.size(); i++) {
            Notification notification = new Notification();
            notification.setCreateDate(LocalDate.now());
            notification.setContent("Hôm nay là sinh nhật của " + employeeList.get(i).getEmployeeName() + ", mã: " + employeeList.get(i).getEmployeeId());
            // lấy account sau khi đăng nhập
//            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            if (principal instanceof UserDetails) {
//                String username = ((UserDetails)principal).getUsername();
//            } else {
//                String username = principal.toString();
//            }
            //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
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
