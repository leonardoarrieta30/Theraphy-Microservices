package com.digitalholics.physiotherapistservice.domain.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private String email;
    private String password;
    private String type;
}
