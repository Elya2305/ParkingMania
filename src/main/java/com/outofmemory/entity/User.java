package com.outofmemory.entity;

import com.outofmemory.entity.common.AuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Table(name = "users")
@Entity
@Data
@ToString(exclude = {"complaints"})
public class User extends AuditableEntity implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String localId;
    @OneToMany(fetch = FetchType.LAZY)
    private List<ComplainInfo> complaints;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return localId;
    }

    public enum Role implements GrantedAuthority {
        USER, ADMIN;

        @Override
        public String getAuthority() {
            return this.name();
        }
    }

    public enum Status {
        ACTIVE, BLOCKED
    }
}
