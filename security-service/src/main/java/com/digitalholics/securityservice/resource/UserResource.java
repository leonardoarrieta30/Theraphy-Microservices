package com.digitalholics.securityservice.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String type;
}
