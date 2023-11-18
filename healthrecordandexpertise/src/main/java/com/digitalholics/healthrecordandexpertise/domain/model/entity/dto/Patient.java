package com.digitalholics.healthrecordandexpertise.domain.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String photoUrl;
    private String birthday;
    private String location;
    private Integer medicalHistoryId;
}
