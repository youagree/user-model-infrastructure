package ru.unit_techno.user.model.impl.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.unit_techno.user.model.impl.dto.ActivateDto;
import ru.unit_techno.user.model.impl.dto.DeleteUserDto;
import ru.unit_techno.user.model.impl.dto.RestorePasswordDto;
import ru.unit_techno.user.model.impl.dto.UserDto;
import ru.unit_techno.user.model.impl.entity.RoleEntity;
import ru.unit_techno.user.model.impl.entity.UserEntity;
import ru.unit_techno.user.model.impl.exception.LoginAlreadyExistException;
import ru.unit_techno.user.model.impl.mapper.UserMapper;
import ru.unit_techno.user.model.impl.repository.UserRepository;
import ru.unit_techno.user.model.impl.service.util.RandomCodeGenerator;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

@Data
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(s);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (RoleEntity role : user.getRoleType()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleType().getValue()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword().toLowerCase(), grantedAuthorities);
    }

    @Transactional
    public UserDto addUser(UserDto userDto) {
        checkRootUserExist();
        UserEntity byEmail = userRepository.findByEmail(userDto.getEmail());

        if (byEmail != null) {
            throw new LoginAlreadyExistException("user with this email is already be used");
        }

        UserEntity createdUser = userMapper.toDomain(userDto);
        createdUser.setActive(false);
        createdUser.setActivationCode(RandomCodeGenerator.generateRandomTemporaryCode());
        createdUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        createdUser.setCreated(new Timestamp(System.currentTimeMillis()));

        sendMessage(createdUser.getEmail(), createdUser.getActivationCode());
        //save after success sending, if not success try AGAIN
        userRepository.save(createdUser);
        return userMapper.toDto(createdUser);
    }

    @Transactional
    public void deleteUser(DeleteUserDto deleteUserDto) {
        if (userRepository.findByEmail(deleteUserDto.getUserLogin()) != null) {
            userRepository.deleteByEmail(deleteUserDto.getUserLogin());
        } else {
            throw new EntityNotFoundException("This user was not found");
        }
    }

    @Transactional
    public void restoreUser(RestorePasswordDto restorePasswordDto) {
        String activationCode = RandomCodeGenerator.generateRandomTemporaryCode();
        userRepository.updateActivationCode(activationCode, restorePasswordDto.getEmail());
        sendMessage(restorePasswordDto.getEmail(), activationCode);
    }

    private void sendMessage(String email, String activationCode) {
        //TODO обернуть в трай
        if (email != null) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Service. Your activation code is: %s",
                    email,
                    activationCode
            );
            mailService.send(email, "Activation code", message);
        }
    }

    private void checkRootUserExist() {
        UserEntity rootUser = userRepository.findByEmail("user@ariss.com");

        if (rootUser != null) {
            rootUser.setExpired(true);
            userRepository.save(rootUser);
        }
    }

    public void activateUser(ActivateDto activateDto) {
        userRepository.findByActivationCode(activateDto.getActivationCode())
                .ifPresentOrElse(userEntity -> {
                            userEntity.setActive(true);
                            userEntity.setPassword(passwordEncoder.encode(activateDto.getPassword().toLowerCase(Locale.ROOT)));
                            userRepository.save(userEntity);
                        },
                        () -> {
                            throw new EntityNotFoundException("user not exist");
                        }
                );
    }
}
