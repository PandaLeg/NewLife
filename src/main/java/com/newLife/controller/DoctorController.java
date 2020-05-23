package com.newLife.controller;


import com.newLife.domain.Clinic;
import com.newLife.domain.Doctor;
import com.newLife.domain.Patient;
import com.newLife.domain.Request;
import com.newLife.repo.DoctorRepo;
import com.newLife.service.DoctorService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class DoctorController {
    private final DoctorService doctorService;
    private final DoctorRepo doctorRepo;

    public DoctorController(DoctorService doctorService, DoctorRepo doctorRepo) {
        this.doctorService = doctorService;
        this.doctorRepo = doctorRepo;
    }

    @GetMapping("/doctor/{id}")
    public Doctor getOneDoctor(@PathVariable(value = "id") Doctor doctor){
        return doctor;
    }

    @GetMapping("/doctors")
    public List<Doctor> getAllClinic() {
        return doctorRepo.findAll();
    }

    @GetMapping("/patients-doctor-list")
    public Set<Patient> getAllPatientsOfDoctor(@AuthenticationPrincipal Doctor doctor) {
        return doctor.getPatients();
    }

    @PostMapping("/send-request-doctor/{id}")
    public Request sendRequest(
            @PathVariable("id") Doctor doctor,
            @AuthenticationPrincipal Clinic clinic,
            @AuthenticationPrincipal Patient patient) {
        return doctorService.sendRequest(doctor, patient, clinic);
    }

    @DeleteMapping("/accept-request-doctor/{id}")
    public void acceptRequest(
            @PathVariable("id") Request request) {
        doctorService.accept(request);
    }
}
