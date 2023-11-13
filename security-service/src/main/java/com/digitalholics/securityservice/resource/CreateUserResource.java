package com.digitalholics.securityservice.resource;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResource {
    @NotBlank
    @NotNull
    @Size(max = 50)
    @Column(unique = true)
    private String firstname;

    @NotBlank
    @NotNull
    @Size(max = 50)
    @Column(unique = true)
    private String lastname;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

}
