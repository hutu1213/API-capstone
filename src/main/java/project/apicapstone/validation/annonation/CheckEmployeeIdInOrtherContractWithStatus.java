package project.apicapstone.validation.annonation;

import project.apicapstone.validation.validator.CheckEmployeeIdInContractValidator;
import project.apicapstone.validation.validator.CheckEmployeeIdInOrtherContractWithStatusValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = CheckEmployeeIdInOrtherContractWithStatusValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface CheckEmployeeIdInOrtherContractWithStatus {
    String message() default "Nhân viên có hợp đồng còn hiệu lực";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
