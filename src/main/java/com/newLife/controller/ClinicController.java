package com.newLife.controller;


import com.newLife.domain.*;
import com.newLife.repo.ClinicRepo;
import com.newLife.repo.DoctorRepo;
import com.newLife.repo.PatientRepo;
import com.newLife.repo.RequestRepo;
import com.newLife.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ClinicController {
    private final ClinicRepo clinicRepo;
    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;
    private final ClinicService clinicService;
    private final RequestRepo requestRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public ClinicController(ClinicRepo clinicRepo, DoctorRepo doctorRepo, PatientRepo patientRepo,
                            ClinicService clinicService, RequestRepo requestRepo) {
        this.clinicRepo = clinicRepo;
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
        this.clinicService = clinicService;
        this.requestRepo = requestRepo;
    }

    @GetMapping("/clinic/{id}")
    public Clinic getOneClinic(@PathVariable("id") Clinic clinic) {
        return clinic;
    }

    @GetMapping("/hospitals")
    public List<Clinic> getAllClinic() {
        List<Clinic> clinics = new LinkedList<>();
        for(Clinic clinic : clinicRepo.findAll()){
            if(!clinic.getUsername().equals("admin")) {
                clinics.add(clinic);
            }
        }
        return clinics;
    }

    @GetMapping("/clinic-roles/{id}")
    public Set<Role> getAllRoles(@PathVariable("id") Clinic clinic){
        return clinic.getRoles();
    }

    @GetMapping("/list-requests")
    public List<Request> getAllRequest(
            @AuthenticationPrincipal Clinic clinic,
            @AuthenticationPrincipal Doctor doctor) {
        List<Request> requests;
        if (clinic != null) {
            requests = requestRepo.findByClinic_Id(clinic.getId());
        } else if (doctor != null) {
            requests = requestRepo.findByDoctor_Id(doctor.getId());
        } else {
            return null;
        }
        return requests;
    }

    @GetMapping("/doctors-list-clinic")
    public Set<Doctor> getAllDoctors(@AuthenticationPrincipal Clinic clinic) {
        Clinic currentClinic = clinicRepo.findByUsername(clinic.getUsername());
        return currentClinic.getDoctors();
    }

    @GetMapping("/doctors-list-clinic/{id}")
    public Set<Doctor> getAllDoctorsForProfile(@PathVariable("id") Clinic clinic) {
        return clinic.getDoctors();
    }

    @GetMapping("/patients-list-clinic/{id}")
    public Set<Patient> getAllPatientsForProfile(@PathVariable("id") Clinic clinic) {
        return clinic.getPatients();
    }

    @GetMapping("/patients-set")
    public Set<Patient> getAllPatientsOfClinic(
            @AuthenticationPrincipal Clinic clinic,
            @AuthenticationPrincipal Doctor doctor) {
        Set<Patient> patients = null;
        if (clinic != null) {
            Clinic currentClinic = clinicRepo.findByUsername(clinic.getUsername());
            patients = currentClinic.getPatients();
        }
        if (doctor != null) {
            patients = doctor.getPatients();
        }
        return patients;
    }

    @PostMapping("/send-request-clinic/{id}")
    public Request sendRequest(
            @PathVariable("id") Clinic clinic,
            @AuthenticationPrincipal Doctor doctor,
            @AuthenticationPrincipal Patient patient) {
        return clinicService.sendRequest(doctor, patient, clinic);
    }


    @PostMapping(value = "/update-profile-avatar")
    public ResponseEntity<Clinic> updateProfileClinicAvatar(
            @AuthenticationPrincipal Clinic currentClinic,
            @AuthenticationPrincipal Doctor currentDoctor,
            @AuthenticationPrincipal Patient currentPatient,
            @RequestParam("picture") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            saveFile(currentClinic, currentDoctor, currentPatient, file);
        }
        if (currentClinic != null) {
            clinicRepo.save(currentClinic);
        } else if (currentDoctor != null) {
            doctorRepo.save(currentDoctor);
        } else {
            patientRepo.save(currentPatient);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void saveFile(Clinic clinic, Doctor doctor, Patient patient, MultipartFile file
    ) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            if (clinic != null) {
                clinic.setClinicPicture(resultFileName);
            }
            if (doctor != null) {
                doctor.setDoctorPicture(resultFileName);
            }
            if (patient != null) {
                patient.setPatientPicture(resultFileName);
            }
        }
    }

    @PutMapping("/update-clinic-profile")
    public Clinic updateProfileClinic(
            @AuthenticationPrincipal Clinic currentClinic,
            @RequestBody Clinic clinic
    ) {
        if (clinic.getUsername() != null && !clinic.getUsername().equals(currentClinic.getUsername())) {
            currentClinic.setUsername(clinic.getUsername());
        }
        if (clinic.getPhone() != null && !clinic.getPhone().equals(currentClinic.getPhone())) {
            currentClinic.setPhone(clinic.getPhone());
        }
        if (clinic.getCity() != null && !clinic.getCity().equals(currentClinic.getCity())) {
            currentClinic.setCity(clinic.getCity());
        }
        if (clinic.getAddress() != null && !clinic.getAddress().equals(currentClinic.getAddress())) {
            currentClinic.setAddress(clinic.getAddress());
        }
        return clinicRepo.save(currentClinic);
    }


    @PutMapping("/update-visit-clinic/{id}")
    public Clinic updateVisit(
            @PathVariable(value = "id") Clinic clinic) {
        clinic.setLastVisit(LocalDateTime.now());
        return clinicRepo.save(clinic);
    }

    @DeleteMapping("/accept-request-clinic/{id}")
    public void acceptRequest(
            @PathVariable("id") Request request
    ) {
        clinicService.accept(request);
    }

    @DeleteMapping("/cancel-request-clinic/{id}")
    public void cancelRequest(
            @PathVariable("id") Request request
    ) {
        clinicService.cancel(request);
    }

    @GetMapping("/check-binding-clinic/{id}")
    public Boolean getCheckBinding(
            @PathVariable("id") Clinic clinic,
            @AuthenticationPrincipal Doctor doctor,
            @AuthenticationPrincipal Patient patient
    ) {
        if (doctor != null) {
            for (Doctor doc : clinic.getDoctors()) {
                if (doc.getId().equals(doctor.getId())) {
                    return true;
                }
            }
            return false;
        }
        if (patient != null) {
            for (Patient pat : clinic.getPatients()) {
                if (pat.getId().equals(patient.getId())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @DeleteMapping("/cancel-binding-clinic/{id}")
    public void cancelBindingDoctorFromClinic(
            @PathVariable("id") Clinic clinic,
            @AuthenticationPrincipal Doctor doctor,
            @AuthenticationPrincipal Patient patient
    ) {
        clinicService.cancelBindingFromClinic(clinic, doctor, patient);
    }

    @PutMapping("/set-role-clinic/{id}")
    public Clinic setRole(
            @PathVariable(value = "id") Clinic clinic,
            @RequestBody Map<String, Object> roles
    ){
        clinic.getRoles().clear();

        clinic.getRoles().add(Role.CLINIC);

        if(roles.get("adminRole").equals(true)){
            clinic.getRoles().add(Role.ADMIN);
        }

        return clinicRepo.save(clinic);
    }
}
