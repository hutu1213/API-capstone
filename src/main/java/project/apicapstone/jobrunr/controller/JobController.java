package project.apicapstone.jobrunr.controller;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/job")
public class JobController {
    private EmployeeService employeeService;
    private JobScheduler jobScheduler;

    private JobService jobService;

    public JobController(EmployeeService employeeService, JobScheduler jobScheduler, JobService jobService) {
        this.employeeService = employeeService;
        this.jobScheduler = jobScheduler;
        this.jobService = jobService;
    }


    //    @GetMapping(value = "/birth-date")
//    public ResponseEntity remindBirthDateJob() {
//        // final JobId enqueuedJobId = jobScheduler.<JobService>enqueue(myService -> myService.doBirthDate(token));
//        jobScheduler.<JobService>enqueue(myService -> myService.doBirthDate());
//
//
//        return ResponseEntity.ok().build();
//    }
    @GetMapping(value = "/birth-date")
    public ResponseEntity remindBirthDateJob() {
        // final JobId enqueuedJobId = jobScheduler.<JobService>enqueue(myService -> myService.doBirthDate(token));
        jobScheduler.<JobService>enqueue(myService -> myService.doBirthDate());



        //////////////////////////////////////////////////
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/birth-date-token/{token}")
    public ResponseEntity remindBirthDateJobWithToken(@PathVariable String token) {
        // final JobId enqueuedJobId = jobScheduler.<JobService>enqueue(myService -> myService.doBirthDate(token));
        //jobScheduler.<JobService>enqueue(myService -> myService.doBirthDateWithToken(token));
        jobScheduler.scheduleRecurrently(
                Cron.daily(),
                () -> jobService.doBirthDateWithToken(token));
        return ResponseEntity.ok("Successful");
    }

    //    @GetMapping(value = "/birth-date")
//    public String remindBirthDateJob( ) {
////        final JobId enqueuedJobId = jobScheduler.<JobService>enqueue(myService -> myService.doBirthDate(token));
////    jobScheduler.<JobService>enqueue(myService -> myService.doBirthDate());
//       // jobScheduler.enqueue(() -> jobService.doBirthDate(token));   //with param
////   jobScheduler.scheduleRecurrently(
////            Cron.daily(),
////            () -> jobService.doBirthDate()); // ********* tại sao dùng scheduleRecurrently mà nó k hiện trong dashboard*********



//        jobScheduler.schedule(
//                LocalDateTime.now().plusSeconds(10),
//                () -> jobService.doBirthDate());
//        //return ResponseEntity.ok().build();
//        return "hi";
//    }


}
