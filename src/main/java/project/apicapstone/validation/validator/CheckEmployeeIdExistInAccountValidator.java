package project.apicapstone.validation.validator;

import project.apicapstone.entity.Account;
import project.apicapstone.entity.Contract;
import project.apicapstone.service.AccountService;
import project.apicapstone.service.EmployeeService;
import project.apicapstone.validation.annonation.CheckEmployeeIdExistInAccount;
import project.apicapstone.validation.annonation.CheckEmployeeIdExistInContract;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class CheckEmployeeIdExistInAccountValidator implements ConstraintValidator<CheckEmployeeIdExistInAccount, String> {
    private String message;
    private AccountService accountService;

    public CheckEmployeeIdExistInAccountValidator(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void initialize(CheckEmployeeIdExistInAccount constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        List<Account> accountList = accountService.existByEmployeeId(s);
        if (accountList.size() > 0) {
            return false;
        }
        return true;
    }
}
