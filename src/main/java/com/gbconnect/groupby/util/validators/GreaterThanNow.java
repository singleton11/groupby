package com.gbconnect.groupby.util.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = GreaterThanNowValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GreaterThanNow {
    String message() default "Should be greater than now";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
