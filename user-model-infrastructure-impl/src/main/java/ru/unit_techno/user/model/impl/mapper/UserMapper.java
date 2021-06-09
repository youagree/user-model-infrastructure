
package ru.unit_techno.user.model.impl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.unit_techno.user.model.impl.dto.UserDto;
import ru.unit_techno.user.model.impl.entity.UserEntity;

@Mapper(uses = RoleMapper.class)
public interface UserMapper {

    @Mapping(target = "activationCode", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    UserEntity toDomain(UserDto userDto);

    UserDto toDto(UserEntity entity);
}