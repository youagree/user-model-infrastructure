package ru.unit_techno.user.model.impl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.unit_techno.user.model.impl.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    void deleteByEmail(String email);

    Optional<UserEntity> findByActivationCode(String activationCode);

    @Modifying
    @Query("update UserEntity set activationCode = :activationCode where email = :email")
    void updateActivationCode(@Param(value = "activationCode") String activationCode, @Param(value = "email") String email);
}
