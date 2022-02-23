package project.apicapstone.validation.annonation;

import project.apicapstone.validation.validator.CheckEmployeeIdExistInAccountValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = CheckEmployeeIdExistInAccountValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface CheckEmployeeIdExistInAccount {
    String message() default "Nhân viên đã có tài khoản";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
