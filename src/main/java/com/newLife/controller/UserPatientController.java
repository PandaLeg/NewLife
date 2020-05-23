package com.newLife.controller;

import com.newLife.domain.Clinic;
import com.newLife.domain.Doctor;
import com.newLife.domain.Patient;
import com.newLife.repo.ChildRepo;
import com.newLife.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

@Controller
public class UserPatientController {
    @Value("${spring.profiles.active}")
    private String profile;

    private final UserService userService;
    private final ChildRepo childRepo;

    public UserPatientController(UserService userService, ChildRepo childRepo) {
        this.userService = userService;
        this.childRepo = childRepo;
    }

    @GetMapping("/state")
    public String getState(
            @AuthenticationPrincipal Patient patient,
            @AuthenticationPrincipal Clinic clinic,
            @AuthenticationPrincipal Doctor doctor,
            Model model){
        HashMap<Object, Object> data = new HashMap<>();

        data.put("children", childRepo.findByPatient_Id(patient.getId()));

        userService.getAllProfiles(clinic, doctor, patient, data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        model.addAttribute("stateData", data);
        return "state";
    }

    @GetMapping("/add-child")
    public String addChild(
            @AuthenticationPrincipal Patient patient,
            @AuthenticationPrincipal Clinic clinic,
            @AuthenticationPrincipal Doctor doctor,
            Model model){
        HashMap<Object, Object> data = new HashMap<>();

        data.put("patient", patient);

        userService.getAllProfiles(clinic, doctor, patient, data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        model.addAttribute("childData", data);
        return "addChild";
    }
}
