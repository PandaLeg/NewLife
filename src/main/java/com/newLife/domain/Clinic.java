package com.newLife.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "username"})
public class Clinic implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.FullClinic.class)
    private Long id;

    @NotBlank(message = "username can't be empty!")
    @Length(min = 3, max = 20)
    @JsonView(Views.FullClinic.class)
    private String username;
    @NotBlank(message = "password can't be empty!")
    @Length(min = 6)
    @JsonView(Views.FullClinic.class)
    private String password;
    @NotBlank(message = "email can't be empty")
    @Email
    @Length(min = 6)
    @JsonView(Views.FullClinic.class)
    private String email;
    @NotBlank(message = "name clinic can't be empty")
    @JsonView(Views.FullClinic.class)
    private String nameClinic;
    @NotBlank(message = "address can't be empty")
    @JsonView(Views.FullClinic.class)
    private String address;
    @NotBlank(message = "city can't be empty")
    @Length(min = 3)
    @JsonView(Views.FullClinic.class)
    private String city;
    @JsonView(Views.FullClinic.class)
    private String phone;
    @JsonView(Views.FullClinic.class)
    private String clinicPicture;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.FullClinic.class)
    private LocalDateTime lastVisit;
    @JsonView(Views.FullClinic.class)
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "clinic_role", joinColumns = @JoinColumn(name = "clinic_id"))
    @Enumerated(EnumType.STRING)
    @JsonView(Views.FullClinic.class)
    private Set<Role> roles;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonView(Views.FullClinic.class)
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    private Set<Doctor> doctors = new HashSet<>();

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    @JsonView(Views.FullClinic.class)
    private Set<Patient> patients = new HashSet<>();

    public Clinic() {
    }


    public Clinic(String username, String password, String email, String nameClinic, String address, String city,
                  String clinicPicture) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nameClinic = nameClinic;
        this.address = address;
        this.city = city;
        this.clinicPicture = clinicPicture;
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
