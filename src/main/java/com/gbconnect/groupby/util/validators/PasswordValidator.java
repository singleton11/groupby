package com.gbconnect.groupby.util.validators;

import com.gbconnect.groupby.domain.Login;
import com.gbconnect.groupby.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, Object> {

    @Autowired
    private UserService userService;

    public void initialize(Password constraint) {
    }

    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        Login login = (Login) obj;
        UserDetails userDetails = this.userService.loadUserByUsername(login.getEmail());
        return userDetails != null && userService.checkPassword(userDetails, login.getPassword());
    }
}
