
package ru.unit_techno.user.model.impl.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.unit_techno.user.model.impl.dto.UserDto;
import ru.unit_techno.user.model.impl.mapper.UserMapper;
import ru.unit_techno.user.model.impl.repository.UserRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ui/${spring.application.name}")
public class UserResource {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto createUser(UserDto userDto) {
        return null;
    }
}