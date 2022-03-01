package project.apicapstone.jobrunr.service;

import com.google.firebase.messaging.*;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.spring.annotations.Recurring;
import org.springframework.stereotype.Service;
import project.apicapstone.entity.Applicant;
import project.apicapstone.entity.Employee;
import project.apicapstone.repository.ApplicantRepository;
import project.apicapstone.repository.EmployeeRepository;
import project.apicapstone.service.ApplicantService;
import project.apicapstone.service.EmployeeService;
import project.apicapstone.service.MailService;

import java.time.LocalDate;
import java.util.*;

@Service
public class JobService {
    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;
    private MailService mailService;
    private ApplicantService applicantService;
    private ApplicantRepository applicantRepository;

    public JobService(EmployeeService employeeService, EmployeeRepository employeeRepository, MailService mailService, ApplicantService applicantService, ApplicantRepository applicantRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
        this.mailService = mailService;
        this.applicantService = applicantService;
        this.applicantRepository = applicantRepository;
    }

    @Recurring(id = "birth-date-job", cron = "*/59 * * * *")
    @Job(name = "Birthday job")
    public String doBirthDate() throws FirebaseMessagingException {
        List<Message> messageList = new ArrayList<>();
        for (int i = 0; i < checkBirthDate().size(); i++) {
            Message message = Message.builder()
                    .setToken("e2CssvfecJIa3G-7F6NXZy:APA91bERZI3Skw-pUOywmuWeh_qaz-nL64R2F6CgLpp9InS7spDz-lSA6KGlkT0HlAbyhU8B9BuTcx6tJ65T6MTfNbX798yJHlGtAibFhr6GZ8jM5VAr8i1v8A884QMYG7LGgd2UPQS7")
                    .setNotification(new Notification("Happy birthday", "Hôm nay là sinh nhật của: " + checkBirthDate().get(i).getEmployeeName() + ", Mã: " + checkBirthDate().get(i).getEmployeeId()))
//                    .putData("content", "Happy birthday")
//                    .putData("body", "Hôm nay là sinh nhật của" + checkBirthDate().get(i).getEmployeeName())
                    .build();
            messageList.add(message);
            FirebaseMessaging.getInstance().send(message);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(messageList);
        return jsonOutput;
    }


    public List<Employee> checkBirthDate() {
        List<Employee> employeeList = employeeService.checkBirthDate(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue());
        return employeeList;
    }

    @Job(name = "Birthday job with token")
    public String doBirthDateWithToken(String token) throws FirebaseMessagingException {
        List<Message> messageList = new ArrayList<>();
        for (int i = 0; i < checkBirthDate().size(); i++) {
            Message message = Message.builder()
                    .setToken(token)
                    .setNotification(new Notification("Happy birthday", "Hôm nay là sinh nhật của: " + checkBirthDate().get(i).getEmployeeName() + ", Mã: " + checkBirthDate().get(i).getEmployeeId()))
                    .build();
            messageList.add(message);
            FirebaseMessaging.getInstance().send(message);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(messageList);
        return jsonOutput;

    }

    @Recurring(id = "Send-mail-when-reject-applicant", cron = "* */59 * * *")
    @Job(name = "Send mail when reject applicant")
    public void sendMailRejectApplicant() {
        String status = "Rớt";
        List<Applicant> applicantList = applicantService.getAllByStatus(status);
        for (int i = 0; i < applicantList.size(); i++) {
            mailService.sendEmailRejectApplicant(applicantList.get(i));
            applicantList.get(i).setCheckSendMail(1);
            applicantRepository.save(applicantList.get(i));
        }

    }
}
