package project.apicapstone.validation.validator;

import project.apicapstone.service.EmployeeService;
import project.apicapstone.validation.annonation.CheckEmployeeIdInOrtherRecruitmentWithStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckEmployeeIdInOrtherRecruitmentWithStatusValidator implements ConstraintValidator<CheckEmployeeIdInOrtherRecruitmentWithStatus, String> {
private final EmployeeService employeeService;

    public CheckEmployeeIdInOrtherRecruitmentWithStatusValidator(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void initialize(CheckEmployeeIdInOrtherRecruitmentWithStatus constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String STATUS = "Chưa duyệt";
        boolean check = employeeService.getEmployeeByRequestAndStatus(s, STATUS);
        if (check) {
            return false;
        } else {
            return true;
        }
    }
}
