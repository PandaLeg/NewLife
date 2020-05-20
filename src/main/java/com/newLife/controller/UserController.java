package com.newLife.controller;


import com.newLife.domain.Clinic;
import com.newLife.domain.Doctor;
import com.newLife.repo.ClinicRepo;
import com.newLife.repo.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;

@Controller
public class UserController {
    private final ClinicRepo clinicRepo;
    private final RequestRepo requestRepo;

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    public UserController(ClinicRepo clinicRepo, RequestRepo requestRepo) {
        this.clinicRepo = clinicRepo;
        this.requestRepo = requestRepo;
    }

    @GetMapping("/clinics")
    public String allClinic(Model model) {
        model.addAttribute("clinicData", clinicRepo.findAll());
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "clinic";
    }

    @GetMapping("/clinic-profile/{id}")
    public String clinicProfile(
            @PathVariable("id") Clinic clinic,
            @AuthenticationPrincipal Clinic currentClinic,
            @AuthenticationPrincipal Doctor currentDoctor,
            Model model) {
        HashMap<Object, Object> data = new HashMap<>();

        data.put("clinicProfile", clinic);

        if (currentClinic != null) {
            data.put("checkRole", false);
        } else {
            data.put("checkRole", true);
        }
        if (currentDoctor != null) {
            data.put("doctorProfile", currentDoctor);
        } else {
            data.put("doctorProfile", null);
        }
        model.addAttribute("isDevMode", "dev".equals(profile));
        model.addAttribute("profileData", data);
        return "clinicProfile";
    }

    @GetMapping("/requests")
    public String request(
            @AuthenticationPrincipal Clinic clinic,
            @AuthenticationPrincipal Doctor doctor,
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

        model.addAttribute("isDevMode", "dev".equals(profile));
        model.addAttribute("requestData", data);
        return "listRequest";
    }

    @GetMapping("/list-doctors")
    public String doctors(
            @AuthenticationPrincipal Clinic clinic,
            Model model) {
        HashMap<Object, Object> data = new HashMap<>();

        data.put("doctors", clinic.getDoctors());

        model.addAttribute("isDevMode", "dev".equals(profile));
        model.addAttribute("doctorData", data);
        return "listDoctors";
    }


    // Этот метод был сделан для того, чтобы вернуть id клиники, к которой привязан текущий доктор
    @GetMapping("/doctor-clinic")
    public String getClinic(
            @AuthenticationPrincipal Doctor doctor) {
        Long byId = clinicRepo.findByClinic_Id(doctor.getId());

        System.out.println(byId);
        return "listClinic";
    }
}
