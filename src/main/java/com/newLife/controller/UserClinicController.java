package com.newLife.controller;


import com.newLife.domain.Clinic;
import com.newLife.domain.Doctor;
import com.newLife.domain.Patient;
import com.newLife.repo.ClinicRepo;
import com.newLife.repo.RequestRepo;
import com.newLife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;

@Controller
public class UserClinicController {
    private final ClinicRepo clinicRepo;
    private final RequestRepo requestRepo;
    private final UserService userService;

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    public UserClinicController(ClinicRepo clinicRepo, RequestRepo requestRepo, UserService userService) {
        this.clinicRepo = clinicRepo;
        this.requestRepo = requestRepo;
        this.userService = userService;
    }

    @GetMapping("/clinics")
    public String allClinic(
            @AuthenticationPrincipal Clinic clinic,
            @AuthenticationPrincipal Doctor doctor,
            @AuthenticationPrincipal Patient patient,
            Model model) {
        HashMap<Object, Object> data = new HashMap<>();

        userService.getAllProfiles(clinic, doctor, patient, data);

        data.put("allClinics", clinicRepo.findAll());

        model.addAttribute("clinicData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "clinic";
    }

    @GetMapping("/clinic-profile/{id}")
    public String clinicProfile(
            @PathVariable("id") Clinic clinic,
            @AuthenticationPrincipal Clinic currentClinic,
            @AuthenticationPrincipal Doctor currentDoctor,
            @AuthenticationPrincipal Patient currentPatient,
            Model model) {
        HashMap<Object, Object> data = new HashMap<>();

        data.put("currentProfileClinic", clinic);

        if (currentClinic != null) {
            data.put("checkRole", false);
        } else {
            data.put("checkRole", true);
        }

        userService.getAllProfiles(currentClinic, currentDoctor, currentPatient, data);

        model.addAttribute("isDevMode", "dev".equals(profile));
        model.addAttribute("profileData", data);
        return "clinicProfile";
    }

    @GetMapping("/requests")
    public String request(
            @AuthenticationPrincipal Clinic clinic,
            @AuthenticationPrincipal Doctor doctor,
            @AuthenticationPrincipal Patient patient,
            Model model) {
        HashMap<Object, Object> data = new HashMap<>();

        if (clinic != null) {
            data.put("requests", requestRepo.findByClinic_Id(clinic.getId()));
            data.put("profileClinic", clinic);
        } else if (doctor != null) {
            data.put("requests", requestRepo.findByDoctor_Id(doctor.getId()));
            data.put("profileDoctor", doctor);
        } else {
            data.put("requests", null);
        }

        data.put("profilePatient", patient);

        model.addAttribute("isDevMode", "dev".equals(profile));
        model.addAttribute("requestData", data);
        return "listRequest";
    }

    @GetMapping("/list-doctors")
    public String doctors(
            @AuthenticationPrincipal Clinic clinic,
            @AuthenticationPrincipal Doctor doctor,
            @AuthenticationPrincipal Patient patient,
            Model model) {
        HashMap<Object, Object> data = new HashMap<>();

        data.put("doctors", clinic.getDoctors());

        userService.getAllProfiles(clinic, doctor, patient, data);

        model.addAttribute("isDevMode", "dev".equals(profile));
        model.addAttribute("doctorData", data);
        return "listDoctors";
    }

    @GetMapping("/list-patients")
    public String patients(
            @AuthenticationPrincipal Clinic clinic,
            @AuthenticationPrincipal Doctor doctor,
            @AuthenticationPrincipal Patient patient,
            Model model
    ) {
        HashMap<Object, Object> data = new HashMap<>();

        if(clinic != null) {
            data.put("patients", clinic.getPatients());
        }
        if(doctor != null) {
            data.put("patients", doctor.getPatients());
        }
        userService.getAllProfiles(clinic, doctor, patient, data);

        model.addAttribute("isDevMode", "dev".equals(profile));
        model.addAttribute("patientData", data);

        return "listPatients";
    }

    /*// Этот метод был сделан для того, чтобы вернуть id клиники, к которой привязан текущий доктор
    @GetMapping("/doctor-clinic")
    public String getClinic(
            @AuthenticationPrincipal Doctor doctor) {
        Long byId = clinicRepo.findByClinic_Id(doctor.getId());

        System.out.println(byId);
        return "listClinic";
    }*/
}
