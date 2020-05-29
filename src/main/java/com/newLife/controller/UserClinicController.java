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

    /*// Этот метод был сделан для того, чтобы вернуть id клиники, к которой привязан текущий доктор
    @GetMapping("/doctor-clinic")
    public String getClinic(
            @AuthenticationPrincipal Doctor doctor) {
        Long byId = clinicRepo.findByClinic_Id(doctor.getId());

        System.out.println(byId);
        return "listClinic";
    }*/
}
