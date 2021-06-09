
package ru.unit_techno.user.model.impl.dto;

import lombok.Data;
import ru.unit_techno.user.model.impl.entity.enums.RoleType;

@Data
public class RoleDto {
    private RoleType roleType;
}