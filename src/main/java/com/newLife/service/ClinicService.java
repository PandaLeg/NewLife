package com.newLife.service;

import com.newLife.domain.Clinic;
import com.newLife.domain.Doctor;
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

    public Request sendRequest(Doctor doctor, Clinic clinic) {
        Request request = new Request();

        request.setDoctor(doctor);
        request.setClinic(clinic);
        request.setMessage("Приймите меня!");

        return requestRepo.save(request);
    }


    public void accept(Request request) {
        Clinic clinicFromBD = request.getClinic();
        Doctor doctorFromBd = request.getDoctor();


        clinicFromBD.getDoctors().add(doctorFromBd);

        clinicRepo.save(clinicFromBD);
        requestRepo.delete(request);
    }
}
