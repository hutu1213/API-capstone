package project.apicapstone.validation.validator;

import project.apicapstone.common.util.ValidatorUtils;
import project.apicapstone.service.TrainingCourseService;
import project.apicapstone.validation.annonation.FindContractId;
import project.apicapstone.validation.annonation.FindCourseId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FindCourseIdValidator implements ConstraintValidator<FindCourseId, String> {
    private String message;
    private TrainingCourseService trainingCourseService;
    public FindCourseIdValidator(TrainingCourseService trainingCourseService){
        this.trainingCourseService=trainingCourseService;
    }
    @Override
    public void initialize(FindCourseId constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean isExisted = trainingCourseService.isExisted(s);

        if (isExisted) {
            return true;
        } else {
            ValidatorUtils.addError(constraintValidatorContext, message);
            return false;

        }
    }
}
