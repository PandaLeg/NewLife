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
@ToString(of = {"id", "username"})
public class Patient implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.FullPatient.class)
    private Long id;

    @NotBlank(message = "username can't be empty!")
    @Length(min = 3, max = 20)
    @JsonView(Views.FullPatient.class)
    private String username;
    @NotBlank(message = "password can't be empty!")
    @Length(min = 6)
    @JsonView(Views.FullPatient.class)
    private String password;
    @NotBlank(message = "email can't be empty")
    @Email
    @Length(min = 6)
    @JsonView(Views.FullPatient.class)
    private String email;
    @NotBlank(message = "firstName can't be empty")
    @Length(min = 2)
    @JsonView(Views.FullPatient.class)
    private String firstName;
    @NotBlank(message = "surname can't be empty")
    @Length(min = 3)
    @JsonView(Views.FullPatient.class)
    private String surname;
    @JsonView(Views.FullPatient.class)
    private String patientPicture;
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

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    @JsonView(Views.FullPatient.class)
    private Set<Child> children = new HashSet<>();


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    @JsonView(Views.FullPatient.class)
    private Clinic clinic;

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
