package ru.unit_techno.user.model.impl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.unit_techno.user.model.impl.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    void deleteByEmail(String email);

    Optional<UserEntity> findByActivationCode(String activationCode);
}
