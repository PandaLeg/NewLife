package com.newLife.service;

import com.newLife.domain.Clinic;
import com.newLife.domain.Doctor;
import com.newLife.domain.Patient;
import com.newLife.domain.Request;
import com.newLife.repo.ClinicRepo;
import com.newLife.repo.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicService {
    private final RequestRepo requestRepo;
    private final ClinicRepo clinicRepo;

    @Autowired
    public ClinicService(RequestRepo requestRepo, ClinicRepo clinicRepo) {
        this.requestRepo = requestRepo;
        this.clinicRepo = clinicRepo;
    }

    public Request sendRequest(Doctor doctor, Patient patient, Clinic clinic) {
        Request request = new Request();

        if(doctor != null) {
            request.setDoctor(doctor);
        }
        if(patient != null){
            request.setPatient(patient);
        }

        request.setClinic(clinic);
        request.setMessage("Приймите меня!");

        return requestRepo.save(request);
    }


    public void accept(Request request) {
        Clinic clinicFromBD = request.getClinic();
        Doctor doctorFromBD = request.getDoctor();
        Patient patientFromBD = request.getPatient();

        if(doctorFromBD != null) {
            clinicFromBD.getDoctors().add(doctorFromBD);
        }
        if(patientFromBD != null){
            clinicFromBD.getPatients().add(patientFromBD);
        }

        clinicRepo.save(clinicFromBD);
        requestRepo.delete(request);
    }
}
