package com.gbconnect.groupby.domain;

import com.gbconnect.groupby.util.validators.UniqueEmail;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

@Data
public class UserRequest {
    @NotNull
    @Email
    @UniqueEmail
    private String email;
    @NotNull
    private String password;
}
