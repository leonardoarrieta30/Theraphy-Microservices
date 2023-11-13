package com.digitalholics.patientservice.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreatePatientResource {

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String firstname;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String lastname;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 300)
    private String photoUrl;

    @NotNull
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String birthday;

    @NotNull
    @NotBlank
    @Size(max = 300)
    private String appointmentQuantity;

    @NotNull
    @NotBlank
    private String location;

    private Integer userId;

}
