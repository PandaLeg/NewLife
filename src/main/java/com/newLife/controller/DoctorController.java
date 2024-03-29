package com.newLife.controller;


import com.newLife.domain.*;
import com.newLife.repo.ClinicRepo;
import com.newLife.repo.DoctorRepo;
import com.newLife.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class DoctorController {
    private final DoctorService doctorService;
    private final DoctorRepo doctorRepo;
    private final ClinicRepo clinicRepo;

    @Autowired
    public DoctorController(DoctorService doctorService, DoctorRepo doctorRepo, ClinicRepo clinicRepo) {
        this.doctorService = doctorService;
        this.doctorRepo = doctorRepo;
        this.clinicRepo = clinicRepo;
    }

    @GetMapping("/doctor/{id}")
    public Doctor getOneDoctor(
            @PathVariable("id") Doctor doctor) {
        return doctor;
    }

    @GetMapping("/doctors")
    public List<Doctor> getAllDoctor() {
        return doctorRepo.findAll();
    }

    @GetMapping("/doctor-roles/{id}")
    public Set<Role> getAllRoles(@PathVariable("id") Doctor doctor){
        return doctor.getRoles();
    }
    @GetMapping("/affiliation-to-clinic/{id}")
    public Doctor affiliationToClinic(
            @PathVariable("id") Doctor doctor,
            @AuthenticationPrincipal Patient patient) {
        Long doctorFromClinic = doctor.getClinic().getId();
        Long patientFromClinic = patient.getClinic().getId();

        if (doctorFromClinic == null) {
            return null;
        }

        if (!doctorFromClinic.equals(patientFromClinic)) {
            return null;
        }

        return doctor;
    }

    @GetMapping("/patients-list-doctor")
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


    @PutMapping("/update-doctor-profile")
    public Doctor updateProfileDoctor(
            @AuthenticationPrincipal Doctor currentDoctor,
            @RequestBody Doctor doctor
    ) {
        if (doctor.getUsername() != null && !doctor.getUsername().equals(currentDoctor.getUsername())) {
            currentDoctor.setUsername(doctor.getUsername());
        }
        if (doctor.getExperience() != null && !doctor.getExperience().equals(currentDoctor.getExperience())) {
            currentDoctor.setExperience(doctor.getExperience());
        }
        if (doctor.getPatients() != null && !doctor.getPatients().equals(currentDoctor.getPosition())) {
            currentDoctor.setPosition(doctor.getPosition());
        }

        return doctorRepo.save(currentDoctor);
    }

    @PutMapping("/update-visit-doctor/{id}")
    public Doctor updateVisit(
            @PathVariable(value = "id") Doctor doctor) {
        doctor.setLastVisit(LocalDateTime.now());
        return doctorRepo.save(doctor);
    }

    @DeleteMapping("/accept-request-doctor/{id}")
    public void acceptRequest(
            @PathVariable("id") Request request) {
        doctorService.accept(request);
    }

    @DeleteMapping("/cancel-request-doctor/{id}")
    public void cancelRequest(
            @PathVariable("id") Request request) {
        doctorService.cancel(request);
    }

    @GetMapping("/check-binding-doctor/{id}")
    public Boolean getCheckBinding(
            @PathVariable("id") Doctor doctor,
            @AuthenticationPrincipal Patient patient,
            @AuthenticationPrincipal Clinic clinic
    ) {
        if (patient != null) {
            return doctor.getPatients().contains(patient);
        }
        if (clinic != null) {
            Clinic currentClinic = clinicRepo.findByUsername(clinic.getUsername());
            for (Doctor doc : currentClinic.getDoctors()) {
                if (doc.getId().equals(doctor.getId())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @DeleteMapping("/cancel-binding-from-doctor/{id}")
    public void cancelBindingFromDoctor(
            @PathVariable("id") Doctor doctor,
            @AuthenticationPrincipal Patient patient,
            @AuthenticationPrincipal Clinic clinic) {
        doctorService.cancelBinding(doctor, patient, clinic);
    }

    @PutMapping("/set-role-doctor/{id}")
    public Doctor setRole(
            @PathVariable("id") Doctor doctor,
            @RequestBody Map<String, Object> roles
    ) {
        doctor.getRoles().clear();

        doctor.getRoles().add(Role.DOCTOR);

        if(roles.get("adminRole").equals(true)){
            doctor.getRoles().add(Role.ADMIN);
        }

        return doctorRepo.save(doctor);
    }
}
