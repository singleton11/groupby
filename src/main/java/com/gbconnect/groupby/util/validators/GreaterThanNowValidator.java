package com.gbconnect.groupby.util.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;

public class GreaterThanNowValidator implements ConstraintValidator<GreaterThanNow, Calendar> {
    @Override
    public void initialize(GreaterThanNow greaterThanNow) {

    }

    @Override
    public boolean isValid(Calendar calendar, ConstraintValidatorContext constraintValidatorContext) {
        return Calendar.getInstance().compareTo(calendar) < 0;
    }
}
