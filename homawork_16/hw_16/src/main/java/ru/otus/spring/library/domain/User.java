package ru.otus.spring.library.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
@SequenceGenerator(name = "S_USERS", sequenceName = "S_USERS", initialValue = 999, allocationSize = 1)
public class User implements UserDetails {
    @Id
    private Long id;
    private String name;
    private String password;
    @Column(name = "is_locked")
    private boolean isLocked;

    public User() {
    }

    public User(Long id, String name, String password, boolean isLocked) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.isLocked = isLocked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
