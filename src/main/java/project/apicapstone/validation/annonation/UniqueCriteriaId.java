package project.apicapstone.validation.annonation;

import project.apicapstone.validation.validator.UniqueAccountIdValidator;
import project.apicapstone.validation.validator.UniqueCriteriaIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UniqueCriteriaIdValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface UniqueCriteriaId {
    String message() default "Mã tiêu chí bị trùng";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
