package project.apicapstone.validation.validator;

import project.apicapstone.service.AccountService;
import project.apicapstone.validation.annonation.FindAccountId;
import project.apicapstone.validation.annonation.FindUsername;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FindUsernameValidator implements ConstraintValidator<FindUsername, String> {
    private String message;
    private AccountService accountService;
    public FindUsernameValidator( AccountService accountService){
        this.accountService=accountService;
    }
    @Override
    public void initialize(FindUsername constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(!accountService.existsAccountByUsername(s)){
            return false;
        }
        return true;
    }
}
