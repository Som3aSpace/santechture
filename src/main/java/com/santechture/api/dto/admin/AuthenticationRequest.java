package com.santechture.api.dto.admin;

import com.santechture.api.annotation.SecurityValid;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SecurityValid
@Data
public class AuthenticationRequest {
    @Valid
    @NotNull
    private String username;
    @NotNull
    private String password;
}
