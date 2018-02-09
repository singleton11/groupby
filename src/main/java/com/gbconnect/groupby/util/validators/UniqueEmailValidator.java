package com.gbconnect.groupby.util.validators;

import com.gbconnect.groupby.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserRepository userRepository;

    public void initialize(UniqueEmail constraint) {
    }

    public boolean isValid(String obj, ConstraintValidatorContext context) {
        return !this.userRepository.existsByUsername(obj);
    }
}
