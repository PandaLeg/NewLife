package com.newLife.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Table(name = "clinic")
@Data
@EqualsAndHashCode(of = {"id"})
public class Clinic implements UserDetails, Serializable {
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
    @NotBlank(message = "name clinic can't be empty")
    private String nameClinic;
    @NotBlank(message = "address can't be empty")
    private String address;
    @NotBlank(message = "city can't be empty")
    @Length(min = 3)
    private String city;
    private String phone;
    private String clinicPicture;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastVisit;
    @JsonView(Views.FullClinic.class)
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "clinic_role", joinColumns = @JoinColumn(name = "clinic_id"))
    @Enumerated(EnumType.STRING)
    @JsonView(Views.FullClinic.class)
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "clinic_doctors",
            // Тобишь тут мы выступаем в роли кого-то(clinic), на кого подписываются(doctor)
            joinColumns = {@JoinColumn(name = "clinic_id")},
            inverseJoinColumns = {@JoinColumn(name = "doctor_id")}
    )
    @JsonView(Views.FullClinic.class)
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    private Set<Doctor> doctors = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "clinic_patients",
            // Тобишь тут мы выступаем в роли кого-то(clinic), на кого подписываются(patient)
            joinColumns = {@JoinColumn(name = "clinic_id")},
            inverseJoinColumns = {@JoinColumn(name = "patient_id")}
    )

    @JsonView(Views.FullClinic.class)
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
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

    boolean checkBindingDoctorToClinic(Doctor doctor){
        return doctors.contains(doctor);
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
