package ru.unit_techno.user.model.impl.entity;

import lombok.Data;
import ru.unit_techno.user.model.impl.entity.enums.RoleType;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
@SequenceGenerator(name = "role_id_seq", sequenceName = "role_id_seq")
public class RoleEntity {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_seq")
    private Long roleId;
    @Column(name = "role_type")
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
}
