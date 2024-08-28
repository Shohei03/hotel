package com.example.Hotel.validation;

import com.example.Hotel.web.process.ReserveForm;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckDateRangeValidator implements ConstraintValidator<CheckDateRange, ReserveForm> {

    @Override
    public boolean isValid(ReserveForm form, ConstraintValidatorContext context) {
        if (form.getCheckin_date() == null || form.getCheckout_date() == null) {
            return true;  // どちらかが null の場合はバリデーションしない
        }
        return form.getCheckin_date().compareTo(form.getCheckout_date()) < 0;
    }
}



