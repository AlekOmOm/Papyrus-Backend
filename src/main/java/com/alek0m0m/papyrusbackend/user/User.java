package com.alek0m0m.papyrusbackend.user;

import com.Alek0m0m.library.jpa.BaseEntity;
import com.alek0m0m.papyrusbackend.field.Field;
import com.alek0m0m.papyrusbackend.resource.Resource;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails {

    private String name;
    private String email;
    private String password;
    private String role;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Field field;

    @ManyToMany
    @JoinTable(
            name = "user_resource",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
    private List<Resource> savedResources = new ArrayList<>();

    public User() {
        this.field = new Field("root", new ArrayList<>());
    }

    // Setters (returning User)
    public User setId(long id) {
        this.id = id;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setRole(String role) {
        this.role = role;
        return this;
    }

    public User setField(Field field) {
        this.field = field;
        return this;
    }

    public User setSavedResources(List<Resource> savedResources) {
        this.savedResources = savedResources;
        return this;
    }

    // Method to set authorities
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.role = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);
    }

    // Spring security methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> role);
    }

    @Override
    public String getUsername() {
        return email;
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
}