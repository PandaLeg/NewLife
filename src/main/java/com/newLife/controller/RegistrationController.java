package com.newLife.controller;

import com.newLife.domain.*;
import com.newLife.repo.ChildRepo;
import com.newLife.repo.ClinicRepo;
import com.newLife.repo.DoctorRepo;
import com.newLife.repo.PatientRepo;
import com.newLife.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collections;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RegistrationController {
    private final ClinicRepo clinicRepo;
    private final DoctorRepo doctorRepo;
    private final PasswordEncoder passwordEncoder;
    private final PatientRepo patientRepo;
    private final ChildRepo childRepo;
    private final JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public RegistrationController(ClinicRepo clinicRepo, DoctorRepo doctorRepo, PasswordEncoder passwordEncoder,
                                  PatientRepo patientRepo, ChildRepo childRepo, JwtUtil jwtUtil) {
        this.clinicRepo = clinicRepo;
        this.doctorRepo = doctorRepo;
        this.passwordEncoder = passwordEncoder;
        this.patientRepo = patientRepo;
        this.childRepo = childRepo;
        this.jwtUtil = jwtUtil;
    }


    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to me";
    }

    @RequestMapping(value = "/authenticate",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        Authentication authenticate = null;
        try {
            authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("invalid username/password");
        }
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String jwt = jwtUtil.generateToken(authRequest.getUserName());
        UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
        System.out.println(jwt);
        return jwt;
    }

    @PostMapping("/registration-clinic")
    public Clinic addClinic(@Valid @RequestBody Clinic clinic) {
        Clinic clinicFromDb = clinicRepo.findByUsername(clinic.getUsername());
        Doctor doctorFromDb = doctorRepo.findByUsername(clinic.getUsername());
        Patient patientFromDb = patientRepo.findByUsername(clinic.getUsername());

        if (clinicFromDb != null) {
            return null;
        }
        if (doctorFromDb != null) {
            return null;
        }
        if (patientFromDb != null) {
            return null;
        }
        clinic.setActive(true);
        clinic.setLastVisit(LocalDateTime.now());
        clinic.setRoles(Collections.singleton(Role.Clinic));
        clinic.setPassword(passwordEncoder.encode(clinic.getPassword()));

        return clinicRepo.save(clinic);
    }

    @PostMapping("/registration-doctor")
    public Doctor addDoctor(@Valid @RequestBody Doctor doctor) {
        Clinic clinicFromDb = clinicRepo.findByUsername(doctor.getUsername());
        Doctor doctorFromDb = doctorRepo.findByUsername(doctor.getUsername());
        Patient patientFromDb = patientRepo.findByUsername(doctor.getUsername());

        if (clinicFromDb != null) {
            return null;
        }
        if (doctorFromDb != null) {
            return null;
        }
        if (patientFromDb != null) {
            return null;
        }
        doctor.setActive(true);
        doctor.setLastVisit(LocalDateTime.now());
        doctor.setRoles(Collections.singleton(Role.Doctor));
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));

        return doctorRepo.save(doctor);
    }

    @PostMapping("/registration-patient")
    public Patient addPatient(@Valid @RequestBody Patient patient) {
        Clinic clinicFromDb = clinicRepo.findByUsername(patient.getUsername());
        Doctor doctorFromDb = doctorRepo.findByUsername(patient.getUsername());
        Patient patientFromDb = patientRepo.findByUsername(patient.getUsername());

        if (clinicFromDb != null) {
            return null;
        }
        if (doctorFromDb != null) {
            return null;
        }
        if (patientFromDb != null) {
            return null;
        }
        patient.setActive(true);
        patient.setLastVisit(LocalDateTime.now());
        patient.setRoles(Collections.singleton(Role.Patient));
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        return patientRepo.save(patient);
    }
}
