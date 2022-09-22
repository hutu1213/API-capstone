package project.apicapstone.validation.validator;

import project.apicapstone.dto.contract.UpdateContractDto;
import project.apicapstone.service.ContractService;
import project.apicapstone.validation.annonation.CheckEmployeeIdInContract;
import project.apicapstone.validation.annonation.CheckEmployeeIdInOrtherContractWithStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckEmployeeIdInOrtherContractWithStatusValidator implements ConstraintValidator<CheckEmployeeIdInOrtherContractWithStatus, String> {
    private String message;
    private ContractService contractService;
    private final String STATUS = "Còn hiệu lực";

    public CheckEmployeeIdInOrtherContractWithStatusValidator(ContractService contractService) {
        this.contractService = contractService;
    }

    @Override
    public void initialize(CheckEmployeeIdInOrtherContractWithStatus constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean check = contractService.countContractByEmployeeIdAndStatus(s, STATUS);
        if (check) {
            return false;
        } else {
            return true;
        }
    }


}
