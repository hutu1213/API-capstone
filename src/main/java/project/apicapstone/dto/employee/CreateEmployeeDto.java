package project.apicapstone.dto.employee;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.Columns;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;
import project.apicapstone.common.util.DateUtils;
import project.apicapstone.validation.annonation.CheckDate;
import project.apicapstone.validation.annonation.CheckEmployeeId;
import project.apicapstone.validation.annonation.CheckPhoneNumber;
import project.apicapstone.validation.annonation.UniqueEmployeeId;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(exclude = {}, callSuper = false)
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

    @CheckPhoneNumber
    private String phone;

    private String frontIdentityCard;

    private String backIdentityCard;

    private String gender;

    private String address;

    @Email
    private String email;

    private String nationality;

    private String religion;

    private String countryOfCitizenship;

    private String academicLevel;

    private String maritalStatus;

    private String workingStatus;

}
