package ru.unit_techno.user.model.impl.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.unit_techno.user.model.impl.entity.UserEntity;
import ru.unit_techno.user.model.impl.entity.enums.RoleType;
import ru.unit_techno.user.model.impl.repository.UserRepository;

@Data
@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    //TODO прикрутить стандартное назначение роли, разобраться как назначать роль если там Set
    public void createUser (String email, String password, RoleType roleType) {
        UserEntity entity = new UserEntity().setEmail(email).setPassword(password);
        userRepository.save(entity);
    }
}
