package project.apicapstone;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.apicapstone.jobrunr.service.JobService;
import project.apicapstone.service.NotificationService;

@SpringBootTest
class SApiCapstoneApplicationTests {
    @Autowired
    private JobService jobService;
    @Autowired
    private NotificationService notificationService;

    @Test
    void contextLoads() {
    }

    @Test
    void getNotificationContract_Test() {
        jobService.getNotificationContract();
        notificationService.getAllByAccountId("1");
        assert true;
    }
}
