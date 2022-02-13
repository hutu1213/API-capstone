package project.apicapstone.validation.validator;

import project.apicapstone.common.util.ValidatorUtils;
import project.apicapstone.service.AccountService;
import project.apicapstone.service.TrainingCourseService;
import project.apicapstone.validation.annonation.UniqueAccountId;
import project.apicapstone.validation.annonation.UniqueCourseId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCourseIdValidator implements ConstraintValidator<UniqueCourseId, String> {
    private String message;
    private TrainingCourseService service;

    public UniqueCourseIdValidator(TrainingCourseService service) {
        this.service = service;
    }

    @Override
    public void initialize(UniqueCourseId constraintAnnotation) {
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
