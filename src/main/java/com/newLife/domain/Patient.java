package com.newLife.domain;


import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
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
    private Long id;

    @NotBlank(message = "username can't be empty!")
    @Length(min = 3)
    private String username;
    @NotBlank(message = "password can't be empty!")
    @Length(min = 6)
    private String password;
    @NotBlank(message = "email can't be empty")
    @Email
    @Length(min = 6)
    private String email;
    @NotBlank(message = "firstName can't be empty")
    @Length(min = 2)
    private String firstName;
    @NotBlank(message = "surname can't be empty")
    @Length(min = 3)
    private String surname;
    private String patientPicture;
    private boolean active;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastVisit;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "patient_role", joinColumns = @JoinColumn(name = "patient_id"))
    @Enumerated(EnumType.STRING)
    @JsonView(Views.FullPatient.class)
    private Set<Role> roles;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    private Set<Child> children = new HashSet<>();

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
