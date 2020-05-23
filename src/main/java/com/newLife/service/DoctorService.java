package com.newLife.service;

import com.newLife.domain.Clinic;
import com.newLife.domain.Doctor;
import com.newLife.domain.Patient;
import com.newLife.domain.Request;
import com.newLife.repo.DoctorRepo;
import com.newLife.repo.RequestRepo;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    private final RequestRepo requestRepo;
    private final DoctorRepo doctorRepo;

    public DoctorService(RequestRepo requestRepo, DoctorRepo doctorRepo) {
        this.requestRepo = requestRepo;
        this.doctorRepo = doctorRepo;
    }

    public Request sendRequest(Doctor doctor, Patient patient, Clinic clinic) {
        Request request = new Request();

        if (patient != null) {
            request.setPatient(patient);
        }

        if (clinic != null) {
            request.setClinic(clinic);
        }

        request.setDoctor(doctor);
        request.setMessage("Приймите меня!");
        return requestRepo.save(request);
    }

    public void accept(Request request) {
        Doctor doctor = request.getDoctor();
        Patient patient = request.getPatient();
        Clinic clinic = request.getClinic();

        if (patient != null) {
            doctor.getPatients().add(patient);
            /*patient.getDoctors().add(doctor);*/
        }

        if (clinic != null) {
            clinic.getDoctors().add(doctor);
        }

        doctorRepo.save(doctor);
        requestRepo.delete(request);
    }
}
