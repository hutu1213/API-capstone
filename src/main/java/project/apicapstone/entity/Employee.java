package project.apicapstone.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import project.apicapstone.common.entity.BaseEntity;
import project.apicapstone.common.util.DateUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})

    @JoinTable(name = "employee_timesheet", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "timesheet_id"))
    private Set<Timesheet> timesheets = new HashSet<>();

    // relationship employee - dependant 1-N
    @OneToMany(mappedBy = "employees")
    private Set<Dependant> dependants = new HashSet<>();

    //relation employee- skill : N-N
    @JsonIgnore
    @Builder.Default
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})

    @JoinTable(name = "employee_skill", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills = new HashSet<>();

    // relationship employee - contract 1-N
    @OneToMany(mappedBy = "employees")
    private Set<Contract> contracts = new HashSet<>();

    // relationship title - employee 1-N
    @ManyToOne
    @JoinColumn(name = "title_id")
    private Title titles;

    // relationship employee - evaluation 1-N
    @OneToMany(mappedBy = "employee")
    private Set<Evaluation> evaluations = new HashSet<>();

    //relation employee - account: 1-1
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Account account;

    public Employee() {

    }

    public Employee(String employeeId, String employeeName, LocalDate dateBirth, String placeBirth, String phone, String identityCardNum, String placeIdentityCard, String gender, String address, String email, String nationality, String religion, String countryOfCitizenship, String academicLevel, String maritalStatus, Set<Timesheet> timesheets, Set<Dependant> dependants, Set<Skill> skills, Set<Contract> contracts, Title titles, Set<Evaluation> evaluations, Account account) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.dateBirth = dateBirth;
        this.placeBirth = placeBirth;
        this.phone = phone;
        this.identityCardNum = identityCardNum;
        this.placeIdentityCard = placeIdentityCard;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.nationality = nationality;
        this.religion = religion;
        this.countryOfCitizenship = countryOfCitizenship;
        this.academicLevel = academicLevel;
        this.maritalStatus = maritalStatus;
        this.timesheets = timesheets;
        this.dependants = dependants;
        this.skills = skills;
        this.contracts = contracts;
        this.titles = titles;
        this.evaluations = evaluations;
        this.account = account;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getPlaceBirth() {
        return placeBirth;
    }

    public void setPlaceBirth(String placeBirth) {
        this.placeBirth = placeBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentityCardNum() {
        return identityCardNum;
    }

    public void setIdentityCardNum(String identityCardNum) {
        this.identityCardNum = identityCardNum;
    }

    public String getPlaceIdentityCard() {
        return placeIdentityCard;
    }

    public void setPlaceIdentityCard(String placeIdentityCard) {
        this.placeIdentityCard = placeIdentityCard;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getCountryOfCitizenship() {
        return countryOfCitizenship;
    }

    public void setCountryOfCitizenship(String countryOfCitizenship) {
        this.countryOfCitizenship = countryOfCitizenship;
    }

    public String getAcademicLevel() {
        return academicLevel;
    }

    public void setAcademicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Set<Timesheet> getTimesheets() {
        return timesheets;
    }

    public void setTimesheets(Set<Timesheet> timesheets) {
        this.timesheets = timesheets;
    }

    public Set<Dependant> getDependants() {
        return dependants;
    }

    public void setDependants(Set<Dependant> dependants) {
        this.dependants = dependants;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    public Title getTitles() {
        return titles;
    }

    public void setTitles(Title titles) {
        this.titles = titles;
    }

    public Set<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(Set<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
