package com.newLife.controller;

import com.newLife.domain.*;
import com.newLife.repo.ChildRepo;
import com.newLife.repo.ClinicRepo;
import com.newLife.repo.DoctorRepo;
import com.newLife.repo.PatientRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collections;

@RestController
public class RegistrationController {
    private final ClinicRepo clinicRepo;
    private final DoctorRepo doctorRepo;
    private final PasswordEncoder passwordEncoder;
    private final PatientRepo patientRepo;
    private final ChildRepo childRepo;

    public RegistrationController(ClinicRepo clinicRepo, DoctorRepo doctorRepo, PasswordEncoder passwordEncoder,
                                  PatientRepo patientRepo, ChildRepo childRepo) {
        this.clinicRepo = clinicRepo;
        this.doctorRepo = doctorRepo;
        this.passwordEncoder = passwordEncoder;
        this.patientRepo = patientRepo;
        this.childRepo = childRepo;
    }

    @PostMapping("/registration-clinic")
    public Clinic addClinic(@Valid @RequestBody Clinic clinic) {
        Clinic clinicFromDb = clinicRepo.findByUsername(clinic.getUsername());
        Doctor doctorFromDb = doctorRepo.findByUsername(clinic.getUsername());
        Patient patientFromDb = patientRepo.findByUsername(clinic.getUsername());

        if(clinicFromDb != null){
            return null;
        }
        if(doctorFromDb != null){
            return null;
        }
        if(patientFromDb != null){
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

        if(clinicFromDb != null){
            return null;
        }
        if(doctorFromDb != null){
            return null;
        }
        if(patientFromDb != null){
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

        if(clinicFromDb != null){
            return null;
        }
        if(doctorFromDb != null){
            return null;
        }
        if(patientFromDb != null){
            return null;
        }
        patient.setActive(true);
        patient.setLastVisit(LocalDateTime.now());
        patient.setRoles(Collections.singleton(Role.Patient));
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        return patientRepo.save(patient);
    }
}
