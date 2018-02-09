package com.gbconnect.groupby.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRequest {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
