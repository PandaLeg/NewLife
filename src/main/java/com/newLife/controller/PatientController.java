package com.newLife.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.newLife.domain.*;
import com.newLife.repo.ChildRepo;
import com.newLife.repo.DoctorRepo;
import com.newLife.repo.PatientRepo;
import com.newLife.service.PatientService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class PatientController {
    private final ChildRepo childRepo;
    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;
    private final PatientService patientService;

    public PatientController(ChildRepo childRepo, DoctorRepo doctorRepo, PatientRepo patientRepo,
                             PatientService patientService) {
        this.childRepo = childRepo;
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
        this.patientService = patientService;
    }

    @GetMapping("/patient/{id}")
    public Patient getOneClinic(@PathVariable("id") Patient patient) {
        return patient;
    }

    @GetMapping("/child/{id}")
    public Child getOneChild(@PathVariable("id") Child child) {
        return child;
    }

    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    /*@GetMapping("/doctor-patient-list")
    public Set<Doctor> getAllDoctors(@AuthenticationPrincipal Patient patient) {
        return patient.getDoctors();
    }*/

    @GetMapping("/child-patient-list")
    public List<Child> getAllChildren(@AuthenticationPrincipal Patient patient) {
        return childRepo.findByPatient_Id(patient.getId());
    }

    @GetMapping("/children-patient/{id}")
    public List<Child> getChild(
            @PathVariable("id") Patient patient
    ){
        return childRepo.findByPatient_Id(patient.getId());
    }

    @GetMapping("/check-binding-patient/{id}")
    public Boolean getCheckBinding(
            @PathVariable("id") Patient patient,
            @AuthenticationPrincipal Doctor doctor
    ){
        if(doctor != null) {
            return doctor.getPatients().contains(patient);
        }

        return false;
    }
    @PostMapping("/child-add")
    public Child addChild(
            @RequestBody Child child,
            @AuthenticationPrincipal Patient patient) {
        child.setPatient(patient);
        return childRepo.save(child);
    }

    @PutMapping("/update-child-indicators/{id}")
    public Child updateIndicators(
            @PathVariable("id") Child currentChild,
            @RequestBody Child child
    ){
        String[] ignore = {"id", "nameChild", "patient",  "height", "weight"};
        BeanUtils.copyProperties(child,currentChild, ignore);
        return childRepo.save(currentChild);
    }

    @PutMapping("/update-visit-patient/{id}")
    public Patient updateVisit(
            @PathVariable(value = "id") Patient patient){
        patient.setLastVisit(LocalDateTime.now());
        return patientRepo.save(patient);
    }

}
