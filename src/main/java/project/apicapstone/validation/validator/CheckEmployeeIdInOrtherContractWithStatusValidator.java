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

    public CheckEmployeeIdInOrtherContractWithStatusValidator(ContractService contractService) {
        this.contractService = contractService;
    }

    @Override
    public void initialize(CheckEmployeeIdInOrtherContractWithStatus constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String status = "Còn hiệu lực";
        boolean check = contractService.getContractByEmployeeIdAndStatus(s, status);
        if (check) {
            return false;
        } else {
            return true;
        }
    }


}
