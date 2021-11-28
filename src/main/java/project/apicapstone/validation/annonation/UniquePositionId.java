package project.apicapstone.validation.annonation;

import project.apicapstone.validation.validator.UniqueAccountIdValidator;
import project.apicapstone.validation.validator.UniquePositionIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UniquePositionIdValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface UniquePositionId {
    String message() default "Position Id is duplicated";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
