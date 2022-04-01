package project.apicapstone.validation.validator;

import project.apicapstone.common.util.ValidatorUtils;
import project.apicapstone.service.TaskService;
import project.apicapstone.validation.annonation.FindAccountId;
import project.apicapstone.validation.annonation.FindTaskId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FindTaskIdValidator implements ConstraintValidator<FindTaskId, Long> {
    private String message;
    private TaskService taskService;

    public FindTaskIdValidator(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void initialize(FindTaskId constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Long s, ConstraintValidatorContext constraintValidatorContext) {
        boolean isExisted = taskService.isExisted(s);

        if (isExisted) {
            return true;
        } else {
            ValidatorUtils.addError(constraintValidatorContext, message);
            return false;

        }
    }
}
