package com.gbconnect.groupby.domain;

import com.gbconnect.groupby.util.validators.Password;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

@Data
@Password
public class Login {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
}
