package project.apicapstone.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import project.apicapstone.common.util.DateUtils;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Entity
@Table(name = "table_employee")
public class Employee {
    @Id
    @Column
    private String employeeId;
    @Column
    private String employeeName;
    @Column
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate dateBirth;
    @Column
    private String placeBirth;
    @Column
    private String phone;
    @Column
    private String gender;
    @Column
    private String address;
    @Column
    private String email;
    @Column
    private String nationality;
    @Column
    private String religion;
    @Column
    private String ethnic;
    @Column
    private String academicLevel;
    @Column
    private String bankAccountNo;
    @Column
    private String bank;
    @Column
    private String taxIdentificationNo;
    @Column
    private String maritalStatus;
    @Column
//    @Enumerated(EnumType.STRING)
    private String workingStatus;
    @Column
    private String avatar;
    @JsonIgnore
    @Column
    private int monthOfBirth;
    @JsonIgnore
    @Column
    private int dayOfBirth;
    @Column
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate createDate;
    @Column
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate updateDate;
    @Column
    private String backIdentityCard;
    @Column
    private String frontIdentityCard;
    @Column
    private String placeIssue;
    @Column
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate dateIssue;
    //relation employee- timesheet : 1-N
    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private Set<Timesheet> timeSheets = new HashSet<>();

    // relationship employee - dependant 1-N
    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private Set<Dependant> dependants = new HashSet<>();

    //relation employee- skill : N-N
    @JsonIgnore
    @Builder.Default
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "employee_skill", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills = new HashSet<>();

    // relationship employee - contract 1-N
    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private Set<Contract> contracts = new HashSet<>();
//    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Contract contract;


    // relationship title - employee 1-N
    @ManyToOne(fetch = FetchType.LAZY) //// test không cần lazy
    //@JsonBackReference
    //@JsonIgnore
    @JoinColumn(name = "title_id")
    private Title title;

    // relationship employee - evaluation 1-N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    @JsonIgnore
    private Set<Evaluation> evaluations = new HashSet<>();

    //relation employee - account: 1-1
    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY)
    @JsonIgnore
    @PrimaryKeyJoinColumn
    private Account account;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "account_id", referencedColumnName = "accountId")
//    private Account account;

//    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Account account;


    //relation employee-task : N-N
    @JsonIgnore
    @Builder.Default
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "employee_task", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "task_id"))
    private Set<Task> tasks = new HashSet<>();

    //relation employee- training course : N-N
    @JsonIgnore
    @Builder.Default
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "employee_training", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "training_id"))
    private Set<TrainingCourse> trainingCourses = new HashSet<>();

    // relationship employee - trainingProposal 1-N
    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private Set<TrainingProposal> proposals = new HashSet<>();

    //
    // relationship employee - Probation 1-N
    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private Set<Probation> probations = new HashSet<>();

    //relationship workplace - employee : 1 - N
    @ManyToOne(fetch = FetchType.LAZY)//, cascade = CascadeType.ALL
    // @JsonIgnore
    @JoinColumn(name = "workplace_id")
    private Workplace workplace;

    //relationship employee - RecruitmentRequest : 1 - N
    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private Set<RecruitmentRequest> recruitmentRequests = new HashSet<>();

    public void removeTask(Task task) {
        tasks.remove(task);
        task.getEmployees().remove(this);

    }
}
