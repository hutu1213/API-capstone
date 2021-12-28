package project.apicapstone.validation.validator;


import project.apicapstone.common.util.ValidatorUtils;
import project.apicapstone.service.SubareaService;
import project.apicapstone.validation.annonation.FindSubareaId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FindSubareaIdValidator implements ConstraintValidator<FindSubareaId, String> {
    private String message;
    private SubareaService subareaService;
    public FindSubareaIdValidator(SubareaService subareaService){
        this.subareaService=subareaService;
    }
    @Override
    public void initialize(FindSubareaId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean isExisted = subareaService.isExisted(s);
//        if (s.isEmpty()) {
//            ValidatorUtils.addError(constraintValidatorContext, "Account Id not blank");
//            return false;
//        }
        if (isExisted) {
            return true;
        } else {
            ValidatorUtils.addError(constraintValidatorContext, message);
            return false;

        }
    }
}
