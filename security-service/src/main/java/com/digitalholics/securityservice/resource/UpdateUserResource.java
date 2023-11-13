package com.digitalholics.securityservice.resource;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateUserResource {

    /*
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String username;*/

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String type;

}
