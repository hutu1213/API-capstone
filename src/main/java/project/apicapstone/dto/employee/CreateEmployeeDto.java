package project.apicapstone.dto.employee;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.Columns;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;
import project.apicapstone.common.util.DateUtils;
import project.apicapstone.validation.annonation.CheckDate;
import project.apicapstone.validation.annonation.CheckEmployeeId;
import project.apicapstone.validation.annonation.UniqueEmployeeId;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

public class CreateEmployeeDto {
    @CheckEmployeeId
    @UniqueEmployeeId
    private String employeeId;

    @Size(min = 3, max = 25, message = "{employee.name.size}")
    private String employeeName;

    //@CheckDate
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate dateBirth;

    private String placeBirth;

    private String phone;

    private String identityCardNum;

    private String placeIdentityCard;

    private String gender;

    private String address;

    @Email
    private String email;

    private String nationality;

    private String religion;

    private String countryOfCitizenship;

    private String academicLevel;

    private String maritalStatus;

    public CreateEmployeeDto() {

    }

    public CreateEmployeeDto(String employeeId, String employeeName, LocalDate dateBirth, String placeBirth, String phone, String identityCardNum, String placeIdentityCard, String gender, String address, String email, String nationality, String religion, String countryOfCitizenship, String academicLevel, String maritalStatus) {
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
}
