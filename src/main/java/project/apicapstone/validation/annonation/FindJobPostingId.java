package project.apicapstone.validation.annonation;

import project.apicapstone.validation.validator.FindJobPostingIdValidator;
import project.apicapstone.validation.validator.FindTitleIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = FindJobPostingIdValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface FindJobPostingId {
    String message() default "Mã bài đăng tuyển dụng không tìm thấy";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
