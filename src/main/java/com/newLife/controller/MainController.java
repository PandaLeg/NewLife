package com.newLife.controller;

import com.newLife.domain.Clinic;
import com.newLife.domain.Doctor;
import com.newLife.domain.Patient;
import com.newLife.repo.ClinicRepo;
import com.newLife.service.ClinicService;
import com.newLife.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

@Controller
public class MainController {
    private final UserService userService;
    private final ClinicService clinicService;
    private final ClinicRepo clinicRepo;

    @Value("${spring.profiles.active}")
    private String profile;

    public MainController(UserService userService, ClinicService clinicService, ClinicRepo clinicRepo) {
        this.userService = userService;
        this.clinicService = clinicService;
        this.clinicRepo = clinicRepo;
    }

    @GetMapping("/")
    public String main(
            @AuthenticationPrincipal Clinic clinic,
            @AuthenticationPrincipal Doctor doctor,
            @AuthenticationPrincipal Patient patient,
            Model model) {
        HashMap<Object, Object> data = new HashMap<>();

        userService.getAllProfiles(clinic, doctor, patient, data);

        model.addAttribute("isDevMode", "dev".equals(profile));
        model.addAttribute("allData", data);
        return "main";
    }

}
