
package ru.unit_techno.user.model.impl.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private String email;
    private String password;
    private Set<RoleDto> roleType;
}