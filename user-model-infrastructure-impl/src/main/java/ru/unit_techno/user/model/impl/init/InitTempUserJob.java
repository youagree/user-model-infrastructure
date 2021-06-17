
package ru.unit_techno.user.model.impl.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.unit_techno.user.model.impl.entity.RoleEntity;
import ru.unit_techno.user.model.impl.entity.UserEntity;
import ru.unit_techno.user.model.impl.entity.enums.RoleType;
import ru.unit_techno.user.model.impl.repository.UserRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitTempUserJob implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public void run(String... args) {
        List<UserEntity> all = userRepository.findAll();
        RoleEntity entity = new RoleEntity().setRoleType(RoleType.ROOT);
        if (all.isEmpty()) {
            userRepository.save(
                    new UserEntity().setEmail("user@ariss.com")
                            .setPassword(encoder.encode("user1111"))
                            .setActive(true)
                            .setRoleType(Set.of(entity))
                            .setCreated(new Timestamp(System.currentTimeMillis()))
                    //TODO expire date
            );
        }
    }
}