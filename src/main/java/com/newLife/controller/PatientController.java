package com.newLife.controller;

import com.newLife.domain.*;
import com.newLife.repo.ChildRepo;
import com.newLife.repo.DoctorRepo;
import com.newLife.repo.MessageRepo;
import com.newLife.repo.PatientRepo;
import com.newLife.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
public class PatientController {
    private final ChildRepo childRepo;
    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;
    private final MessageRepo messageRepo;
    private final PatientService patientService;

    @Autowired
    public PatientController(ChildRepo childRepo, DoctorRepo doctorRepo, PatientRepo patientRepo,
                             MessageRepo messageRepo, PatientService patientService) {
        this.childRepo = childRepo;
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
        this.messageRepo = messageRepo;
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
    public Set<Child> getAllChildren(@AuthenticationPrincipal Patient patient) {
        return patient.getChildren();
    }

    @GetMapping("/children-patient/{id}")
    public List<Child> getChild(
            @PathVariable("id") Patient patient
    ) {
        return childRepo.findByPatient_Id(patient.getId());
    }

    @GetMapping("/check-binding-patient/{id}")
    public Boolean getCheckBinding(
            @PathVariable("id") Patient patient,
            @AuthenticationPrincipal Doctor doctor

    ) {
        if (doctor != null) {
            return doctor.getPatients().contains(patient);
        }

        return false;
    }

    @GetMapping("/check-binding-clinic-to-patient/{id}")
    public Boolean getCheckBindingTOClinic(
            @PathVariable("id") Patient patient,
            @AuthenticationPrincipal Clinic clinic
    ) {
        if (clinic != null) {
            return clinic.getPatients().contains(patient);
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
    ) {
        if (child.getTemperature() != null) {
            currentChild.setTemperature(child.getTemperature());
        }
        if (child.getPulse() != null) {
            currentChild.setPulse(child.getPulse());
        }
        if (child.getPressure() != null) {
            currentChild.setPressure(child.getPressure());
        }
        return childRepo.save(currentChild);
    }

    @PostMapping("/send-message/{id}")
    public Message sendMessage(
            @PathVariable("id") Patient patient,
            @AuthenticationPrincipal Clinic clinic,
            @RequestBody Message message
    ) {
        if (!StringUtils.isEmpty(message.getDescription())) {
            message.setDescription(message.getDescription());
        } else {
            return null;
        }
        if (!StringUtils.isEmpty(message.getTitle())) {
            message.setTitle(message.getTitle());
        } else {
            return null;
        }
        message.setClinic(clinic);
        if (patient != null) {
            message.setPatient(patient);
        }

        return messageRepo.save(message);
    }

    @GetMapping("/patient-messages-list")
    public List<Message> getMessages(
            @AuthenticationPrincipal Patient patient
    ) {
        return messageRepo.findByPatient_Id(patient.getId());
    }

    @DeleteMapping("/patient-delete-message/{id}")
    public void deleteMessage(
            @PathVariable("id") Message message,
            @AuthenticationPrincipal Patient patient
    ) {
        messageRepo.delete(message);
    }

    @PutMapping("/update-patient-profile")
    public Patient updateProfilePatient(
            @AuthenticationPrincipal Patient currentPatient,
            @RequestBody Patient patient
    ) {
        if (patient.getUsername() != null && !patient.getUsername().equals(currentPatient.getUsername())) {
            currentPatient.setUsername(patient.getUsername());
        }

        return patientRepo.save(currentPatient);
    }

    @PutMapping("/update-visit-patient/{id}")
    public Patient updateVisit(
            @PathVariable(value = "id") Patient patient) {
        patient.setLastVisit(LocalDateTime.now());
        return patientRepo.save(patient);
    }

}
