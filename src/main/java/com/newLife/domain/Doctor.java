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
import java.util.*;

@Entity
@Table(name = "doctor")
@Data
@EqualsAndHashCode(of = {"id"})
public class Doctor implements UserDetails, Serializable {
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
    @NotBlank(message = "position can't be empty")
    @Length(min = 3)
    private String position;
    private String experience;
    private String doctorPicture;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastVisit;
    @JsonView(Views.FullDoctor.class)
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "doctor_role", joinColumns = @JoinColumn(name = "doctor_id"))
    @Enumerated(EnumType.STRING)
    @JsonView(Views.FullDoctor.class)
    private Set<Role> roles;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    @JsonView(Views.FullDoctor.class)
    private Clinic clinic;

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
