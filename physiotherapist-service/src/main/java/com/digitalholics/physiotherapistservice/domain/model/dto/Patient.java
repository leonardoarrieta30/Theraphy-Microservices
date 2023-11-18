package com.digitalholics.physiotherapistservice.domain.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Patient {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String photoUrl;
    private String birthday;
    private String location;
    private Integer userId;
}
