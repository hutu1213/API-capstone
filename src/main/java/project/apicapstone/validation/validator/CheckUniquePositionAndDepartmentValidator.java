package project.apicapstone.validation.validator;

import project.apicapstone.dto.title.CreateTitleDto;
import project.apicapstone.service.TitleService;
import project.apicapstone.validation.annonation.CheckPhoneNumber;
import project.apicapstone.validation.annonation.CheckUniquePositionAndDepartment;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckUniquePositionAndDepartmentValidator implements ConstraintValidator<CheckUniquePositionAndDepartment, CreateTitleDto> {
    private String message;
    private TitleService titleService;

    public CheckUniquePositionAndDepartmentValidator(TitleService titleService) {
        this.titleService = titleService;
    }

    @Override
    public void initialize(CheckUniquePositionAndDepartment constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(CreateTitleDto createTitleDto, ConstraintValidatorContext constraintValidatorContext) {
        String positionId = createTitleDto.getPositionId();
        String departmentId = createTitleDto.getDepartmentId();
        boolean check  = titleService.checkPositionAndDepartment(positionId,departmentId);
        if(check){
            return false;
        }else {
            return true;
        }
    }
}
