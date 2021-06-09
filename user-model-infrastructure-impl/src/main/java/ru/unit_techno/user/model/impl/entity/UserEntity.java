
package ru.unit_techno.user.model.impl.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq")
public class UserEntity implements UserDetails {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "isExpired")
    private boolean isExpired;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_type", referencedColumnName = "role_id")
    private Set<RoleEntity> roleType;

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoleType();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}