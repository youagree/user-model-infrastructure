
package ru.unit_techno.user.model.impl.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.unit_techno.user.model.impl.dto.UserDto;
import ru.unit_techno.user.model.impl.entity.UserEntity;
import ru.unit_techno.user.model.impl.mapper.UserMapper;
import ru.unit_techno.user.model.impl.repository.UserRepository;

import javax.persistence.EntityExistsException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ui/${spring.application.name}")
public class UserResource {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @PostMapping("/create-user")
    public UserDto createUser(@RequestBody UserDto userDto) {

        checkRootUserExist();
        UserEntity byEmail = userRepository.findByEmail(userDto.getEmail());

        if (byEmail != null) {
            throw new EntityExistsException("This email is already be used");
        }

        UserEntity createdUser = userMapper.toDomain(userDto);
        userRepository.save(createdUser);

        return userMapper.toDto(createdUser);
    }

    private void checkRootUserExist() {
        UserEntity rootUser = userRepository.findByEmail("user@ariss.com");

        if (rootUser != null) {
            rootUser.setExpired(true);
            userRepository.save(rootUser);
        }
    }
}