
package ru.unit_techno.user.model.impl.mapper;

import org.mapstruct.Mapper;
import ru.unit_techno.user.model.impl.dto.RoleDto;
import ru.unit_techno.user.model.impl.entity.RoleEntity;

@Mapper
public interface RoleMapper {
    RoleEntity toDomain(RoleDto roleDto);
}