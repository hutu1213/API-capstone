package project.apicapstone.validation.annonation;

import project.apicapstone.validation.validator.CheckUniquePositionAndDepartmentValidator;
import project.apicapstone.validation.validator.FindTaskIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


import static java.lang.annotation.ElementType.TYPE;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = CheckUniquePositionAndDepartmentValidator.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface CheckUniquePositionAndDepartment {
    String message() default "Vị trí và phòng ban đã tồn tại";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
