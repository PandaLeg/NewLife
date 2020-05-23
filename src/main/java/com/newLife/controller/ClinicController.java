package com.newLife.controller;


import com.newLife.domain.Clinic;
import com.newLife.domain.Doctor;
import com.newLife.domain.Patient;
import com.newLife.domain.Request;
import com.newLife.repo.ClinicRepo;
import com.newLife.repo.RequestRepo;
import com.newLife.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class ClinicController {
    private final ClinicRepo clinicRepo;
    private final ClinicService clinicService;
    private final RequestRepo requestRepo;

    @Autowired
    public ClinicController(ClinicRepo clinicRepo, ClinicService clinicService, RequestRepo requestRepo) {
        this.clinicRepo = clinicRepo;
        this.clinicService = clinicService;
        this.requestRepo = requestRepo;
    }

    @GetMapping("/clinic/{id}")
    public Clinic getOneClinic(@PathVariable("id") Clinic clinic) {
        return clinic;
    }

    @GetMapping("/hospitals")
    public List<Clinic> getAllClinic() {
        return clinicRepo.findAll();
    }

    @GetMapping("/request")
    public List<Request> getAllRequest() {
        return requestRepo.findAll();
    }

    @GetMapping("/doctors-list")
    public Set<Doctor> getAllDoctors(@AuthenticationPrincipal Clinic clinic) {
        return clinic.getDoctors();
    }

    @GetMapping("/patients-clinic-list")
    public Set<Patient> getAllPatientsOfClinic(@AuthenticationPrincipal Clinic clinic) {
        return clinic.getPatients();
    }

    @PostMapping("/send-request-clinic/{id}")
    public Request sendRequest(
            @PathVariable("id") Clinic clinic,
            @AuthenticationPrincipal Doctor doctor,
            @AuthenticationPrincipal Patient patient) {
        return clinicService.sendRequest(doctor, patient, clinic);
    }

    @DeleteMapping("/accept-request-clinic/{id}")
    public void acceptRequest(
            @PathVariable("id") Request request) {
        clinicService.accept(request);
    }


}
