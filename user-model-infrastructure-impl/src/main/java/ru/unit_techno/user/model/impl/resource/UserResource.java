
package ru.unit_techno.user.model.impl.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.unit_techno.user.model.impl.dto.UserDto;
import ru.unit_techno.user.model.impl.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ui/${spring.application.name}")
public class UserResource {
    private final UserService userService;

    @PostMapping("/create-user")
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }
}