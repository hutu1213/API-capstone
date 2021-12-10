package project.apicapstone.dto.contract;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import project.apicapstone.common.util.DateUtils;
import project.apicapstone.validation.annonation.FindEmployeeId;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class UpdateContractDto {
    private String contractId;

    //@Size(min = 3, max = 25, message = "{contract.name.size}")
    private String contractName;


    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate startDate;


    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate endDate;

    // @NotBlank(message = "{contract.status.not-blank}")
    private String status;

    //@Size(min = 3, max = 25, message = "{contract.content.size}")
    private String content;

    private double salary;

    private String type;

    @FindEmployeeId
    private String employeeId;
}
