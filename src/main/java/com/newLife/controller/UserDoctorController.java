package com.newLife.controller;

import com.newLife.domain.Clinic;
import com.newLife.domain.Doctor;
import com.newLife.domain.Patient;
import com.newLife.repo.ClinicRepo;
import com.newLife.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;

@Controller
public class UserDoctorController {
    private final UserService userService;
    private final ClinicRepo clinicRepo;

    @Value("${spring.profiles.active}")
    private String profile;

    public UserDoctorController(UserService userService, ClinicRepo clinicRepo) {
        this.userService = userService;
        this.clinicRepo = clinicRepo;
    }

    @GetMapping("/doctor-profile/{id}")
    public String doctorProfile(
            @PathVariable("id") Doctor doctor,
            @AuthenticationPrincipal Clinic currentClinic,
            @AuthenticationPrincipal Doctor currentDoctor,
            @AuthenticationPrincipal Patient currentPatient,
            Model model)
    {
        HashMap<Object, Object> data = new HashMap<>();

        data.put("currentProfileDoctor", doctor);

        // Проверка принадлежности пациента и доктора к одной клинике
        if(currentPatient != null) {
            Long clinicFromDoctor = clinicRepo.findByClinic_IdFromDoctor_Id(doctor.getId());
            Long clinicFromPatient = clinicRepo.findByClinic_IdFromPatient_Id(currentPatient.getId());
            if(!clinicFromDoctor.equals(clinicFromPatient)){
                data.put("checkRole", false);
            }else{
                data.put("checkRole", true);
            }
        }
        if (currentDoctor != null) {
            data.put("checkRole", false);
        } else {
            data.put("checkRole", true);
        }

        userService.getAllProfiles(currentClinic, currentDoctor, currentPatient, data);

        model.addAttribute("isDevMode", "dev".equals(profile));
        model.addAttribute("profileData", data);
        return "doctorProfile";
    }

}
