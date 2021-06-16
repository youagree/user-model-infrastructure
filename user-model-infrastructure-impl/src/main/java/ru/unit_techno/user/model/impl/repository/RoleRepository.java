package ru.unit_techno.user.model.impl.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.unit_techno.user.model.impl.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
