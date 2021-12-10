package project.apicapstone.validation.annonation;

import project.apicapstone.validation.validator.CheckEmployeeIdValidator;
import project.apicapstone.validation.validator.CheckPhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = CheckPhoneNumberValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface CheckPhoneNumber {
    String message() default "Số điện thoại từ 1 đến 12 chữ số";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
