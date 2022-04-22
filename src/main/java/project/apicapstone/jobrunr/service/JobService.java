package project.apicapstone.jobrunr.service;


import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.spring.annotations.Recurring;
import org.springframework.stereotype.Service;
import project.apicapstone.entity.*;
import project.apicapstone.repository.*;

import project.apicapstone.service.*;

import java.time.LocalDate;
import java.util.*;

@Service
public class JobService {
    private final String CRON_12AM = "0 0 * * *";
    private final String CRON_9AM = "0 9 * * *";
    private final EmployeeService employeeService;
    private final MailService mailService;
    private final ApplicantService applicantService;
    private final ApplicantRepository applicantRepository;
    private final String STATUS = "Chưa phù hợp";
    private final String ROLE_TRUONGPHONG = "ROLE_TRUONGPHONG";
    private final String ROLE_QL_NHANVIEN = "ROLE_QL_NHANVIEN";
    private final String ROLE_QL_HOPDONG = "ROLE_QL_HOPDONG";
    private final String CONTRACT_STATUS = "Hết hiệu lực";
    private final String JOB_POSTING_STATUS_EXPIRED = "Dừng tuyển";
    private final String JOB_POSTING_STATUS = "Đang tuyển";
    private final AccountService accountService;
    private final NotificationRepository notificationRepository;
    private final AccountRepository accountRepository;
    private final ContractService contractService;
    private final DependantService dependantService;
    private final ContractRepository contractRepository;
    private final JobPostingRepository jobPostingRepository;
    private final JobPostingService jobPostingService;
    private final RecruitmentRequestService recruitmentRequestService;
private final RequestNotificationRepository requestNotificationRepository;
    public JobService(DependantService dependantService, ContractService contractService, AccountRepository accountRepository, NotificationRepository notificationRepository, AccountService accountService, EmployeeService employeeService, MailService mailService, ApplicantService applicantService, ApplicantRepository applicantRepository, ContractRepository contractRepository, JobPostingRepository jobPostingRepository, JobPostingService jobPostingService, RecruitmentRequestService recruitmentRequestService, RequestNotificationRepository requestNotificationRepository) {
        this.employeeService = employeeService;
        this.dependantService = dependantService;
        this.contractService = contractService;
        this.accountRepository = accountRepository;
        this.mailService = mailService;
        this.applicantService = applicantService;
        this.applicantRepository = applicantRepository;
        this.accountService = accountService;
        this.notificationRepository = notificationRepository;
        this.contractRepository = contractRepository;
        this.jobPostingRepository = jobPostingRepository;
        this.jobPostingService = jobPostingService;
        this.recruitmentRequestService = recruitmentRequestService;
        this.requestNotificationRepository = requestNotificationRepository;
    }

    public List<Employee> checkBirthDate() {
        List<Employee> employeeList = employeeService.checkBirthDate(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue());
        return employeeList;
    }

    @Recurring(id = "Update-contract-expired", cron = CRON_12AM)
    @Job(name = "Update contract expired")
    public void updateContract() {
        List<Contract> contractList = contractService.getContractsByEndDate(LocalDate.now().minusDays(1));
        for (Contract contract : contractList) {
            contract.setStatus(CONTRACT_STATUS);
            contractRepository.save(contract);
        }
    }

    @Recurring(id = "Update-job-posting-expired", cron = CRON_12AM)
    @Job(name = "Update job posting expired")
    public void updateJobPosting() {
        List<JobPosting> jobPostingList = jobPostingService.getJPsByEndDate(LocalDate.now().minusDays(1));
        for (JobPosting jobPosting : jobPostingList) {
            jobPosting.setStatus(JOB_POSTING_STATUS_EXPIRED);
            jobPostingRepository.save(jobPosting);
        }
    }

    @Recurring(id = "Update-job-posting-future", cron = CRON_12AM)
    @Job(name = "Update job posting")
    public void updateJobPostingFuture() {
        List<JobPosting> jobPostingList = jobPostingService.getJPsByStartDate(LocalDate.now());
        for (JobPosting jobPosting : jobPostingList) {
            jobPosting.setStatus(JOB_POSTING_STATUS);
            jobPostingRepository.save(jobPosting);
        }
    }

    @Recurring(id = "Send-mail-when-reject-applicant", cron = CRON_9AM)
    @Job(name = "Send mail when reject applicant")
    public void sendMailRejectApplicant() {
        List<Applicant> applicantList = applicantService.getAllByStatus(STATUS);
        for (Applicant applicant : applicantList) {
            mailService.sendEmailRejectApplicant(applicant);
            applicant.setCheckSendMail(1);
            applicantRepository.save(applicant);
        }
    }

    @Recurring(id = "Get-notification-employee-birthday", cron = CRON_9AM)
    @Job(name = "Get-notification-employee-birthday")
    public void getNotificationEmpl() {
        List<Account> accountList = accountService.getAccountsByRoleName(ROLE_TRUONGPHONG, ROLE_QL_NHANVIEN);
        for (int i = 0; i < checkBirthDate().size(); i++) {
            project.apicapstone.entity.Notification notification = new project.apicapstone.entity.Notification();
            notification.setCreateDate(LocalDate.now());
            notification.setTitle("Thông báo chúc mừng sinh nhật nhân viên");
            notification.setContent("Hôm nay là sinh nhật của " + checkBirthDate().get(i).getEmployeeName() + ", mã: " + checkBirthDate().get(i).getEmployeeId());
            notificationRepository.save(notification);
            for (int j = 0; j < accountList.size(); j++) {
                Account account = accountRepository.getById(accountList.get(j).getAccountId());
                notification.addAccount(account);
                accountRepository.save(account);
            }
        }
    }

    public List<Dependant> checkBirthDateDependant() {
        List<Dependant> dependantList = dependantService.checkBirthDate(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue());
        return dependantList;
    }
//                           BIRTHDAY OF DEPENDANT**********************************************************************
//    @Recurring(id = "Get-notification-dependant-birthday", cron = "0 9 * * *")
//    @Job(name = "Get-notification-dependant-birthday")
//    public void getNotificationDependant() {
//        List<Account> accountList = accountService.getAccountsByRoleName(ROLE_TRUONGPHONG, ROLE_QL_NHANVIEN);
//        for (int i = 0; i < checkBirthDateDependant().size(); i++) {
//            project.apicapstone.entity.Notification notification = new project.apicapstone.entity.Notification();
//            notification.setCreateDate(LocalDate.now());
//            notification.setTitle("Thông báo chúc mừng sinh nhật người phụ thuộc");
//            notification.setContent("Hôm nay là sinh nhật của " + checkBirthDateDependant().get(i).getDependantName() + ", mã: " + checkBirthDateDependant().get(i).getDependantId());
//            notificationRepository.save(notification);
//            for (int j = 0; j < accountList.size(); j++) {
//                Account account = accountRepository.getById(accountList.get(j).getAccountId());
//                notification.addAccount(account);
//                accountRepository.save(account);
//            }
//        }
//
//    }

    @Recurring(id = "Get-notification-contract-before-5-day", cron = CRON_9AM)
    @Job(name = "Get-notification-contract")
    public void getNotificationContract() {
        List<Account> accountList = accountService.getAccountsByRoleName(ROLE_TRUONGPHONG, ROLE_QL_HOPDONG);
        List<Contract> contractList = contractService.getContractsByEndDate(LocalDate.now().plusDays(5));
        for (int i = 0; i < contractList.size(); i++) {
            project.apicapstone.entity.Notification notification = new project.apicapstone.entity.Notification();
            notification.setCreateDate(LocalDate.now());
            notification.setTitle("Thông báo hết hạn hợp đồng");
            notification.setContent("Hợp đồng " + contractList.get(i).getContractName() + ", mã: " + contractList.get(i).getContractId() + " còn 5 ngày nữa sẽ hết hạn.");
            notificationRepository.save(notification);
            for (int j = 0; j < accountList.size(); j++) {
                Account account = accountRepository.getById(accountList.get(j).getAccountId());
                notification.addAccount(account);
                accountRepository.save(account);
            }
        }
    }

    @Recurring(id = "Get-recruitment-request-not-yet", cron = CRON_9AM)
    @Job(name = "Get-notification-recruitment-request")
    public void getNotificationRequest() {
        List<Account> accountList = accountService.getAccountsByRoleName(ROLE_TRUONGPHONG, "ROLE_QL_TUYENDUNG");
        List<RecruitmentRequest> recruitmentRequestList = recruitmentRequestService.getByStatus("Chưa duyệt");
        for (int i = 0; i < recruitmentRequestList.size(); i++) {
            RequestNotification requestNotification = new RequestNotification();
            requestNotification.setCreateDate(LocalDate.now());
            requestNotification.setTitle("Thông báo yêu cầu tuyển dụng chưa được duyệt");
            requestNotification.setRecruitmentRequestId(recruitmentRequestList.get(i).getRecruitmentRequestId());
            requestNotification.setContent("Yêu cầu tuyển dụng " + recruitmentRequestList.get(i).getRecruitmentRequestId() +" chưa được duyệt");
            requestNotificationRepository.save(requestNotification);
            for (int j = 0; j < accountList.size(); j++) {
                Account account = accountRepository.getById(accountList.get(j).getAccountId());
                requestNotification.addAccountToRequestNotifi(account);
                accountRepository.save(account);
            }
        }
    }
    // lấy username sau khi đăng nhập
//            String username = jwtUtils.getUsernameFromToken(jwtUtils.getJwtTokenFromRequest(request));
//            Account account = accountService.findByUsername(username);
//    @Recurring(id = "birth-date-job", cron = "*/59 * * * *")
//    @Job(name = "Birthday job")
//    public String doBirthDate() throws FirebaseMessagingException {
//        List<Message> messageList = new ArrayList<>();
//        for (int i = 0; i < checkBirthDate().size(); i++) {
//            Message message = Message.builder()
//                    .setToken("e2CssvfecJIa3G-7F6NXZy:APA91bERZI3Skw-pUOywmuWeh_qaz-nL64R2F6CgLpp9InS7spDz-lSA6KGlkT0HlAbyhU8B9BuTcx6tJ65T6MTfNbX798yJHlGtAibFhr6GZ8jM5VAr8i1v8A884QMYG7LGgd2UPQS7")
//                    .setNotification(new Notification("Happy birthday", "Hôm nay là sinh nhật của: " + checkBirthDate().get(i).getEmployeeName() + ", Mã: " + checkBirthDate().get(i).getEmployeeId()))
////                    .putData("content", "Happy birthday")
////                    .putData("body", "Hôm nay là sinh nhật của" + checkBirthDate().get(i).getEmployeeName())
//                    .build();
//            messageList.add(message);
//            FirebaseMessaging.getInstance().send(message);
//        }
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        String jsonOutput = gson.toJson(messageList);
//        return jsonOutput;
//    }
//    @Job(name = "Birthday job with token")
//    public String doBirthDateWithToken(String token) throws FirebaseMessagingException {
//        List<Message> messageList = new ArrayList<>();
//        for (int i = 0; i < checkBirthDate().size(); i++) {
//            Message message = Message.builder()
//                    .setToken(token)
//                    .setNotification(new Notification("Happy birthday", "Hôm nay là sinh nhật của: " + checkBirthDate().get(i).getEmployeeName() + ", Mã: " + checkBirthDate().get(i).getEmployeeId()))
//                    .build();
//            messageList.add(message);
//            FirebaseMessaging.getInstance().send(message);
//        }
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        String jsonOutput = gson.toJson(messageList);
//        return jsonOutput;
//
//    }
}



