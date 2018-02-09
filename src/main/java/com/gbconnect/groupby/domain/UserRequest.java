package com.gbconnect.groupby.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

@Data
public class UserRequest {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
}
