package com.example.Hotel.validation;

import jakarta.validation.Constraint;

import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CheckDateRangeValidator.class)
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckDateRange {
    String message() default "チェックイン日はチェックアウト日より前でなければなりません。";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


