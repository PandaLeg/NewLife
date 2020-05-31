package com.newLife.domain;

import com.fasterxml.jackson.annotation.*;
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
import java.util.*;

@Entity
@Table(name = "doctor")
@Data
@EqualsAndHashCode(of = {"id"})
public class Doctor implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.IdUsernameEmailFirstNameSurnamePosition.class)
    private Long id;

    @NotBlank(message = "username can't be empty!")
    @JsonView(Views.IdUsernameEmailFirstNameSurnamePosition.class)
    private String username;
    @NotBlank(message = "password can't be empty!")
    private String password;
    @NotBlank(message = "email can't be empty")
    @Email
    @JsonView(Views.IdUsernameEmailFirstNameSurnamePosition.class)
    private String email;
    @JsonView(Views.IdUsernameEmailFirstNameSurnamePosition.class)
    private String firstName;
    @JsonView(Views.IdUsernameEmailFirstNameSurnamePosition.class)
    private String surname;
    @JsonView(Views.IdUsernameEmailFirstNameSurnamePosition.class)
    private String position;
    @JsonView(Views.FullDoctor.class)
    private String experience;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.FullDoctor.class)
    private LocalDateTime lastVisit;
    @JsonView(Views.FullDoctor.class)
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "doctor_role", joinColumns = @JoinColumn(name = "doctor_id"))
    @Enumerated(EnumType.STRING)
    @JsonView(Views.FullDoctor.class)
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "doctors_patients",
            // Тобишь тут мы выступаем в роли кого-то(doctor), на кого подписываются(patient)
            joinColumns = { @JoinColumn(name = "doctor_id") },
            inverseJoinColumns = { @JoinColumn(name = "patient_id") }
    )
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    @JsonView(Views.FullDoctor.class)
    private Set<Patient> patients = new HashSet<>();

    public Doctor() {
    }

    public Doctor(String username, String password, String email, String firstName, String surname, String position) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.surname = surname;
        this.position = position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
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
}
