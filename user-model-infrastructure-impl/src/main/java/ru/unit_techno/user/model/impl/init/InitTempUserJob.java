
package ru.unit_techno.user.model.impl.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.unit_techno.user.model.impl.entity.RoleEntity;
import ru.unit_techno.user.model.impl.entity.UserEntity;
import ru.unit_techno.user.model.impl.entity.enums.RoleType;
import ru.unit_techno.user.model.impl.repository.RoleRepository;
import ru.unit_techno.user.model.impl.repository.UserRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitTempUserJob implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<UserEntity> all = userRepository.findAll();
        RoleEntity entity = new RoleEntity().setRoleType(RoleType.ROOT);
        if (all.isEmpty()) {
            UserEntity user = userRepository.save(
                    new UserEntity().setEmail("user@ariss.com")
                            .setPassword(encoder.encode("user1111"))
                            .setActive(true)
                    //TODO expire date
            );
            entity.setUser(user);
            roleRepository.save(entity);
        }
    }
}