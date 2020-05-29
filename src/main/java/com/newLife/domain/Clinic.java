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
@Table(name = "clinic")
@Data
public class Clinic implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.IdUsernameEmailNameClinicAddress.class)
    private Long id;
    @NotBlank(message = "username can't be empty!")
    @JsonView(Views.IdUsernameEmailNameClinicAddress.class)
    private String username;
    @NotBlank(message = "password can't be empty!")
    private String password;
    @NotBlank(message = "email can't be empty")
    @Email
    @JsonView(Views.IdUsernameEmailNameClinicAddress.class)
    private String email;
    @JsonView(Views.IdUsernameEmailNameClinicAddress.class)
    private String nameClinic;
    @JsonView(Views.IdUsernameEmailNameClinicAddress.class)
    private String address;
    private String phone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.FullClinic.class)
    private LocalDateTime lastVisit;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "clinic_role", joinColumns = @JoinColumn(name = "clinic_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "clinic_doctors",
            // Тобишь тут мы выступаем в роли кого-то(clinic), на кого подписываются(doctor)
            joinColumns = { @JoinColumn(name = "clinic_id") },
            inverseJoinColumns = { @JoinColumn(name = "doctor_id") }
    )
    private Set<Doctor> doctors = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "clinic_patients",
            // Тобишь тут мы выступаем в роли кого-то(clinic), на кого подписываются(patient)
            joinColumns = { @JoinColumn(name = "clinic_id") },
            inverseJoinColumns = { @JoinColumn(name = "patient_id") }
    )
    private Set<Patient> patients = new HashSet<>();

    public Clinic() {
    }

    public Clinic(String username, String password, String email, String nameClinic, String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nameClinic = nameClinic;
        this.address = address;
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
