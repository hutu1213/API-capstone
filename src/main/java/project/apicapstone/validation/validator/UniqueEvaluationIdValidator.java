package project.apicapstone.validation.validator;

import project.apicapstone.common.util.ValidatorUtils;

import project.apicapstone.service.EvaluationService;

import project.apicapstone.validation.annonation.UniqueEvaluationId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEvaluationIdValidator implements ConstraintValidator<UniqueEvaluationId, String> {
    private String message;
    private EvaluationService evaluationService;

    public UniqueEvaluationIdValidator(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @Override
    public void initialize(UniqueEvaluationId constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean isExisted = evaluationService.isExisted(s);

        if (isExisted) {
            ValidatorUtils.addError(constraintValidatorContext, message);
            return false;
        } else {
            return true;
        }
    }
}
