package project.apicapstone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import project.apicapstone.common.entity.BaseEntity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "table_employee")
public class Employee extends BaseEntity {
    @Column
    private String employeeName;
    @Column
    private String dateBirth;
    @Column
    private String placeBirth;
    @Column
    private String phone;
    @Column
    private String identityCardNum;
    @Column
    private String placeIdentityCard;
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
    private String countryOfCitizenship;
    @Column
    private String academicLevel;
    @Column
    private String maritalStatus;

    //relation employee- timesheet : N-N
    @JsonIgnore
    @Builder.Default
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })

    @JoinTable(name = "employee_timesheet", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "timesheet_id"))
    private Set<Timesheet> timesheets = new HashSet<>();

    // relationship employee - dependant 1-N
    @OneToMany(mappedBy="employees")
    private Set<Dependant> dependants = new HashSet<>();

    //relation employee- skill : N-N
    @JsonIgnore
    @Builder.Default
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })

    @JoinTable(name = "employee_skill", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills = new HashSet<>();

    // relationship employee - contract 1-N
    @OneToMany(mappedBy="employees")
    private Set<Contract> contracts = new HashSet<>();

// relationship title - employee 1-N
@ManyToOne
@JoinColumn(name = "title_id")
private Title titles;

    // relationship employee - evaluation 1-N
    @OneToMany(mappedBy="employee")
    private Set<Evaluation> evaluations = new HashSet<>();

    //relation employee - account: 1-1
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Account account;

}
