package com.newLife.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.newLife.domain.*;
import com.newLife.repo.ClinicRepo;
import com.newLife.repo.RequestRepo;
import com.newLife.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @GetMapping("/list-requests")
    public List<Request> getAllRequest(
            @AuthenticationPrincipal Clinic clinic,
            @AuthenticationPrincipal Doctor doctor) {
        List<Request> requests;
        if (clinic != null) {
            requests = requestRepo.findByClinic_Id(clinic.getId());
        } else if (doctor != null) {
            requests = requestRepo.findByDoctor_Id(doctor.getId());
        } else {
            return null;
        }
        return requests;
    }

    @GetMapping("/doctors-list-clinic")
    public Set<Doctor> getAllDoctors(@AuthenticationPrincipal Clinic clinic) {
        return clinic.getDoctors();
    }

    @GetMapping("/patients-set")
    public Set<Patient> getAllPatientsOfClinic(
            @AuthenticationPrincipal Clinic clinic,
            @AuthenticationPrincipal Doctor doctor) {
        Set<Patient> patients = null;
        if (clinic != null) {
            patients = clinic.getPatients();
        }
        if (doctor != null) {
            patients = doctor.getPatients();
        }
        return patients;
    }

    @PostMapping("/send-request-clinic/{id}")
    public Request sendRequest(
            @PathVariable("id") Clinic clinic,
            @AuthenticationPrincipal Doctor doctor,
            @AuthenticationPrincipal Patient patient) {
        return clinicService.sendRequest(doctor, patient, clinic);
    }

    @PutMapping("/update-visit-clinic/{id}")
    public Clinic updateVisit(
            @PathVariable(value = "id") Clinic clinic) {
        clinic.setLastVisit(LocalDateTime.now());
        return clinicRepo.save(clinic);
    }

    @DeleteMapping("/accept-request-clinic/{id}")
    public void acceptRequest(
            @PathVariable("id") Request request
    ) {
        clinicService.accept(request);
    }

    @DeleteMapping("/cancel-request-clinic/{id}")
    public void cancelRequest(
            @PathVariable("id") Request request
    ) {
        clinicService.cancel(request);
    }

    @GetMapping("/check-binding-clinic/{id}")
    public Boolean getCheckBinding(
            @PathVariable("id") Clinic clinic,
            @AuthenticationPrincipal Doctor doctor,
            @AuthenticationPrincipal Patient patient
    ) {
        if(doctor != null){
            return clinic.getDoctors().contains(doctor);
        }
        if(patient != null){
            return clinic.getPatients().contains(patient);
        }
        return false;
    }

    @DeleteMapping("/cancel-binding-clinic/{id}")
    public void cancelBindingDoctorFromClinic(
            @PathVariable("id") Clinic clinic,
            @AuthenticationPrincipal Doctor doctor,
            @AuthenticationPrincipal Patient patient
    ) {
        clinicService.cancelBindingFromClinic(clinic, doctor, patient);
    }
}
