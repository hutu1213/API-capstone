package project.apicapstone.validation.annonation;

import project.apicapstone.validation.validator.CheckEmployeeIdInOrtherContractWithStatusValidator;
import project.apicapstone.validation.validator.CheckEmployeeIdInOrtherRecruitmentWithStatusValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = CheckEmployeeIdInOrtherRecruitmentWithStatusValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface CheckEmployeeIdInOrtherRecruitmentWithStatus {
    String message() default "Nhân viên có yêu cầu chưa được duyệt";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
