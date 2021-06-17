
package ru.unit_techno.user.model.impl.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.unit_techno.user.model.impl.dto.ActivateDto;
import ru.unit_techno.user.model.impl.dto.DeleteUserDto;
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

    @PostMapping("/activate-user")
    public void activateUser(@Valid @RequestBody ActivateDto activateDto) {
        userService.activateUser(activateDto);
    }

    @DeleteMapping("/delete-user")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@RequestBody DeleteUserDto deleteUserDto) {
        userService.deleteUser(deleteUserDto);
    }
}