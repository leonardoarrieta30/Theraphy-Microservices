package com.digitalholics.therapyservice.domain.model.entity.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Patient {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String photoUrl;
    private String birthday;
    private String appointmentQuantity;
    private String location;
}
