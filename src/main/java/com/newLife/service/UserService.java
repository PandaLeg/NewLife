package com.newLife.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.newLife.domain.Clinic;
import com.newLife.domain.Doctor;
import com.newLife.domain.Patient;
import com.newLife.domain.Views;
import com.newLife.repo.ClinicRepo;
import com.newLife.repo.DoctorRepo;
import com.newLife.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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

    public void getAllProfiles(Clinic clinic,
                               Doctor doctor,
                               Patient patient,
                               HashMap<Object, Object> data) throws JsonProcessingException {
        if (clinic != null) {
            Clinic clinicFromDb = clinicRepo.findByUsername(clinic.getUsername());
            /*String serializedClinic = clinicWriter.writeValueAsString(clinicFromDb);*/
            data.put("profileClinic", clinicFromDb);
        } else if (doctor != null) {
            Doctor doctorFromDb = doctorRepo.findByUsername(doctor.getUsername());
            /*String serializedDoctor = doctorWriter.writeValueAsString(doctorFromDb);*/
            data.put("profileDoctor", doctorFromDb);
        } else if (patient != null) {
            Patient patientFromDb = patientRepo.findByUsername(patient.getUsername());
            /*String serializedPatient = patientWriter.writeValueAsString(patientFromDb);*/
            data.put("profilePatient", patientFromDb);
        } else {
            data.put("profileClinic", null);
            data.put("profileDoctor", null);
            data.put("profilePatient", null);
        }
    }


}
