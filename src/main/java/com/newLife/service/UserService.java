package com.newLife.service;

import com.newLife.domain.Clinic;
import com.newLife.domain.Doctor;
import com.newLife.domain.Patient;
import com.newLife.repo.ClinicRepo;
import com.newLife.repo.DoctorRepo;
import com.newLife.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final ClinicRepo clinicRepo;
    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;

    @Autowired
    public UserService(ClinicRepo clinicRepo, DoctorRepo doctorRepo, PatientRepo patientRepo) {
        this.clinicRepo = clinicRepo;
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Clinic clinic = clinicRepo.findByUsername(username);
        Doctor doctor = doctorRepo.findByUsername(username);
        Patient patient = patientRepo.findByUsername(username);

        if (clinic != null) {
            return clinic;
        } else if (doctor != null) {
            return doctor;
        } else if (patient != null) {
            return patient;
        } else {
            throw new UsernameNotFoundException("Username not found!");
        }

    }
}
