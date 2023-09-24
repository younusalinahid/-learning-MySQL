package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class StudentRequest {
    private String idNo;
    @NotNull(message = "must not be null")
    private String name;
    @Email(message = "invalid email address")
    private String email;
    @Pattern(regexp = "\\d{10}$",message = "invalid mobile number")
    private String mobileNumber;
}
