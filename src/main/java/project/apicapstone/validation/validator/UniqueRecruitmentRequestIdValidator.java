package project.apicapstone.validation.validator;

import project.apicapstone.common.util.ValidatorUtils;
import project.apicapstone.service.RecruitmentRequestService;
import project.apicapstone.validation.annonation.UniqueRecruitmentRequestId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueRecruitmentRequestIdValidator implements ConstraintValidator<UniqueRecruitmentRequestId, String> {
    private String message;
    private final RecruitmentRequestService recruitmentRequestService;

    public UniqueRecruitmentRequestIdValidator(RecruitmentRequestService recruitmentRequestService) {
        this.recruitmentRequestService = recruitmentRequestService;
    }

    @Override
    public void initialize(UniqueRecruitmentRequestId constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean isExisted = recruitmentRequestService.isExisted(s);

        if (isExisted) {
            ValidatorUtils.addError(constraintValidatorContext, message);
            return false;
        } else {
            return true;
        }
    }
}
