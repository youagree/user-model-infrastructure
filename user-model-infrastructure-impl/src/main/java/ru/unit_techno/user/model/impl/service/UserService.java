package ru.unit_techno.user.model.impl.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.unit_techno.user.model.impl.dto.UserDto;
import ru.unit_techno.user.model.impl.entity.UserEntity;
import ru.unit_techno.user.model.impl.entity.enums.RoleType;
import ru.unit_techno.user.model.impl.mapper.UserMapper;
import ru.unit_techno.user.model.impl.repository.UserRepository;

import javax.persistence.EntityExistsException;
import java.util.UUID;

@Data
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MailService mailService;

    //TODO прикрутить стандартное назначение роли, разобраться как назначать роль если там Set
    public void createUser(String email, String password, RoleType roleType) {
        UserEntity entity = new UserEntity().setEmail(email).setPassword(password);
        userRepository.save(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        userRepository.findByEmail(s);

        return null;
    }

    public UserDto addUser(UserDto userDto) {
        checkRootUserExist();
        UserEntity byEmail = userRepository.findByEmail(userDto.getEmail());

        if (byEmail != null) {
            throw new EntityExistsException("This email is already be used");
        }

        UserEntity createdUser = userMapper.toDomain(userDto);
        createdUser.setActive(false);
        createdUser.setActivationCode(UUID.randomUUID().toString());

        userRepository.save(createdUser);

        sendMessage(createdUser);
        return userMapper.toDto(createdUser);
    }

    private void sendMessage(UserEntity user) {
        //TODO обернуть в трай
        if (user.getEmail() != null) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Service. Please, visit next link: http://%s/activate/%s",
                    user.getUsername(),
                    //поменять на наш урл
                    "test-url",
                    user.getActivationCode()
            );
            mailService.send(user.getEmail(), "Activation code", message);
        }
    }

    private void checkRootUserExist() {
        UserEntity rootUser = userRepository.findByEmail("user@ariss.com");

        if (rootUser != null) {
            rootUser.setExpired(true);
            userRepository.save(rootUser);
        }
    }
}
