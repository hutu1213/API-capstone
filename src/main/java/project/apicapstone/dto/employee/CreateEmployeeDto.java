package project.apicapstone.dto.employee;


public class CreateEmployeeDto {

    private String employeeName;

    private String dateBirth;

    private String placeBirth;

    private String phone;

    private String identityCardNum;

    private String placeIdentityCard;

    private String gender;

    private String address;

    private String email;

    private String nationality;

    private String religion;

    private String countryOfCitizenship;

    private String academicLevel;

    private String maritalStatus;

    public CreateEmployeeDto(String employeeName, String dateBirth, String placeBirth, String phone, String identityCardNum, String placeIdentityCard, String gender, String address, String email, String nationality, String religion, String countryOfCitizenship, String academicLevel, String maritalStatus) {
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
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
