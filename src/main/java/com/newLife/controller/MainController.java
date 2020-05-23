package com.newLife.controller;

import com.newLife.domain.Clinic;
import com.newLife.domain.Doctor;
import com.newLife.domain.Patient;
import com.newLife.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

@Controller
public class MainController {
    private final UserService userService;

    @Value("${spring.profiles.active}")
    private String profile;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(
            @AuthenticationPrincipal Clinic clinic,
            @AuthenticationPrincipal Doctor doctor,
            @AuthenticationPrincipal Patient patient,
            Model model) {
        HashMap<Object, Object> data = new HashMap<>();

        userService.getAllProfiles(clinic, doctor, patient, data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        model.addAttribute("dataClinic", data);
        return "main";
    }

    @GetMapping("/registration-clinic")
    public String registrationClinic(Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        data.put("checkClinic", true);
        data.put("checkDoctor", false);
        model.addAttribute("isDevMode", "dev".equals(profile));
        model.addAttribute("regData", data);
        return "registration";
    }

    @GetMapping("/registration-doctor")
    public String registrationDoctor(Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        data.put("checkDoctor", true);
        data.put("checkClinic", false);
        model.addAttribute("isDevMode", "dev".equals(profile));
        model.addAttribute("regData", data);
        return "registration";
    }

    @GetMapping("/registration-patient")
    public String registrationPatient(Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        data.put("checkDoctor", false);
        data.put("checkClinic", false);
        model.addAttribute("isDevMode", "dev".equals(profile));
        model.addAttribute("regData", data);
        return "registration";
    }
}
