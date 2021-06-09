
package ru.unit_techno.user.model.impl.mapper;

import org.mapstruct.Mapper;
import ru.unit_techno.user.model.impl.dto.UserDto;
import ru.unit_techno.user.model.impl.entity.UserEntity;

@Mapper(uses = RoleMapper.class)
public interface UserMapper {

    UserEntity toDomain(UserDto userDto);

    UserDto toDto(UserEntity entity);
}