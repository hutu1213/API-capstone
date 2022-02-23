package project.apicapstone.jobrunr.controller;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.hibernate.service.spi.InjectService;
import org.jobrunr.jobs.JobId;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.entity.Employee;
import project.apicapstone.firebase.dto.NotificationRequestDto;
import project.apicapstone.jobrunr.service.JobService;
import project.apicapstone.service.EmployeeService;

import javax.annotation.PostConstruct;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/job")
public class JobController {
    private EmployeeService employeeService;
    @Autowired
    private JobScheduler jobScheduler;
    @Autowired
    private JobService jobService;

//    @PostConstruct
//    public void JobController() {
//        // sao truyền token vào
//        jobScheduler.scheduleRecurrently("birth-date-token", Cron.daily(), () -> jobService.doBirthDateWithToken("e2CssvfecJIa3G-7F6NXZy:APA91bERZI3Skw-pUOywmuWeh_qaz-nL64R2F6CgLpp9InS7spDz-lSA6KGlkT0HlAbyhU8B9BuTcx6tJ65T6MTfNbX798yJHlGtAibFhr6GZ8jM5VAr8i1v8A884QMYG7LGgd2UPQS7"));
//    }

    // tại sao phải lưu token và notification?

    @GetMapping(value = "/birth-date", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity remindBirthDateJob() throws FirebaseMessagingException {
        String list = jobService.doBirthDate();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/birth-date-token/{token}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity remindBirthDateJobWithToken(@PathVariable String token) throws FirebaseMessagingException {
        String json = jobService.doBirthDateWithToken(token);
        jobScheduler.scheduleRecurrently("birth-date-token", "* */1 * * *", () -> jobService.doBirthDateWithToken(token));// right
        return ResponseEntity.ok(json);
    }

}
