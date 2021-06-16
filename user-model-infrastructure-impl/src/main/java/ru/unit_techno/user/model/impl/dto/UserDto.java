
package ru.unit_techno.user.model.impl.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class UserDto {
    @Email(message = "Email is not correct")
    @NotBlank(message = "Email cannot be empty")
    private String email;
    @Size(min = 6, max = 12)
    @NotBlank(message = "Password cannot be empty")
    private String password;
    @NotEmpty(message = "Input roleType list cannot be empty.")
    private Set<RoleDto> roleType;
}