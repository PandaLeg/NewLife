package com.newLife.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patient")
@Data
@EqualsAndHashCode(of = {"id"})
public class Patient implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.IdUsernameEmailFirstNameSurname.class)
    private Long id;

    @NotBlank(message = "username can't be empty!")
    @JsonView(Views.IdUsernameEmailFirstNameSurname.class)
    private String username;
    @NotBlank(message = "password can't be empty!")
    private String password;
    @NotBlank(message = "email can't be empty")
    @Email
    @JsonView(Views.IdUsernameEmailFirstNameSurname.class)
    private String email;
    @JsonView(Views.IdUsernameEmailFirstNameSurname.class)
    private String firstName;
    @JsonView(Views.IdUsernameEmailFirstNameSurname.class)
    private String surname;
    @JsonView(Views.FullPatient.class)
    private boolean active;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.FullPatient.class)
    private LocalDateTime lastVisit;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "patient_role", joinColumns = @JoinColumn(name = "patient_id"))
    @Enumerated(EnumType.STRING)
    @JsonView(Views.FullPatient.class)
    private Set<Role> roles;

    public Patient() {
    }

    public Patient(String username, String password, String email, String firstName, String surname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.surname = surname;
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
        return isActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }
}
