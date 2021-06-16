package ru.unit_techno.user.model.impl.entity.enums;

import lombok.Getter;

public enum RoleType {
    ROOT("ROOT"),
    ADMIN("ADMIN"),
    SUPER_ADMIN("UBER DEV ROOT");

    @Getter
    private final String value;

    RoleType(String value) {
        this.value = value;
    }
}
