
package ru.unit_techno.user.model.impl.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ActivateDto {
    @Size(min = 8, max = 16)
    @NotBlank(message = "Password cannot be empty")
    @Pattern(regexp = "[a-zA-Z0-9]{8,16}", message = "password not valid")
    private String password;
    @NotBlank(message = "Activation code cannot be empty")
    private String activationCode;
}