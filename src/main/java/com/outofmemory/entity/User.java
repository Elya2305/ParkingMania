package com.outofmemory.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Table(name = "usr")
@Entity
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;
    private String uuid;
    private String username;
    private String email;
    private String password;
    @OneToMany
    private List<ComplainInfo> complaints;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
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
//        return this.status != Status.BLOCKED;
        return true;
    }

    public enum Role {
        USER, ADMIN
    }

    public enum Status {
        ACTIVE, BLOCKED
    }
}
