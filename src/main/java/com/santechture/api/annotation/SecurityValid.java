package com.santechture.api.annotation;

import com.santechture.api.validation.SecurityValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SecurityValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityValid  {
    String message() default  "Duplicated username";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
