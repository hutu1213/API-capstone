package project.apicapstone.validation.validator;

import project.apicapstone.common.util.ValidatorUtils;
import project.apicapstone.service.AccountService;
import project.apicapstone.service.CriteriaService;
import project.apicapstone.validation.annonation.UniqueAccountId;
import project.apicapstone.validation.annonation.UniqueCriteriaId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCriteriaIdValidator implements ConstraintValidator<UniqueCriteriaId, String> {
    private String message;
    private CriteriaService service;
    public UniqueCriteriaIdValidator(CriteriaService service){
        this.service=service;
    }
    @Override
    public void initialize(UniqueCriteriaId constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean isExisted = service.isExisted(s);

        if (isExisted) {
            ValidatorUtils.addError(constraintValidatorContext, message);
            return false;
        } else {
            return true;
        }
    }
}
