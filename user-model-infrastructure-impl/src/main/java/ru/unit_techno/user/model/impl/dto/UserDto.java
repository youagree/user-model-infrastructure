
package ru.unit_techno.user.model.impl.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Set;

@Data
public class UserDto {
    @Email(message = "Email is not correct")
    @NotBlank(message = "Email cannot be empty")
    private String email;
    @Size(min = 8, max = 16)
    @Pattern(regexp = "[a-zA-Z0-9]{8,16}", message = "Wrong password. Use english language.")
    @NotBlank(message = "Password cannot be empty")
    private String password;
    @NotEmpty(message = "Input roleType list cannot be empty.")
    //TODO Сделать сет RoleType вместо RoleDto
    private Set<RoleDto> roleType;
}