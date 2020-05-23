package com.newLife.controller;

import com.newLife.domain.Child;
import com.newLife.domain.Doctor;
import com.newLife.domain.Patient;
import com.newLife.repo.ChildRepo;
import com.newLife.repo.DoctorRepo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {
    private final ChildRepo childRepo;
    private final DoctorRepo doctorRepo;
    public PatientController(ChildRepo childRepo, DoctorRepo doctorRepo) {
        this.childRepo = childRepo;
        this.doctorRepo = doctorRepo;
    }

    @GetMapping("/doctor-patient-list")
    public List<Doctor> getAllDoctors(@AuthenticationPrincipal Patient patient) {
        return doctorRepo.findByDoctor_Id(patient.getId());
    }

    @GetMapping("/child-patient-list")
    public List<Child> getAllChilds(@AuthenticationPrincipal Patient patient) {
        return childRepo.findByPatient_Id(patient.getId());
    }

    @PostMapping("/child-add")
    public Child addChild(
            @RequestBody Child child,
            @AuthenticationPrincipal Patient patient) {
        child.setPatient(patient);
        return childRepo.save(child);
    }
}
