
package ru.unit_techno.user.model.impl.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.unit_techno.user.model.impl.entity.UserEntity;
import ru.unit_techno.user.model.impl.repository.UserRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitTempUserJob implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        List<UserEntity> all = userRepository.findAll();
        if (all.isEmpty()) {
            userRepository.save(
                    new UserEntity().setEmail("user@ariss.com")
                            .setPassword("user1111")
                    //TODO expire date
            );
        }
    }
}