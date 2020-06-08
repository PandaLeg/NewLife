package com.newLife.service;

import com.newLife.domain.Clinic;
import com.newLife.domain.Doctor;
import com.newLife.domain.Patient;
import com.newLife.domain.Request;
import com.newLife.repo.ClinicRepo;
import com.newLife.repo.DoctorRepo;
import com.newLife.repo.PatientRepo;
import com.newLife.repo.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicService {
    private final RequestRepo requestRepo;
    private final ClinicRepo clinicRepo;
    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;
    @Autowired
    public ClinicService(RequestRepo requestRepo, ClinicRepo clinicRepo, DoctorRepo doctorRepo,
                         PatientRepo patientRepo) {
        this.requestRepo = requestRepo;
        this.clinicRepo = clinicRepo;
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
    }

    public Request sendRequest(Doctor doctor, Patient patient, Clinic clinic) {
        Request request = new Request();

        if (doctor != null) {
            request.setDoctor(doctor);
        }
        if (patient != null) {
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

        if (doctorFromBD != null) {
            doctorFromBD.setClinic(clinicFromBD);
            doctorRepo.save(doctorFromBD);
        }
        if (patientFromBD != null) {
            /*clinicFromBD.getPatients().add(patientFromBD);
            clinicRepo.save(clinicFromBD);*/
            patientFromBD.setClinic(clinicFromBD);
            patientRepo.save(patientFromBD);
        }

        requestRepo.delete(request);
    }

    public void cancel(Request request) {
        requestRepo.delete(request);
    }

    public void cancelBindingFromClinic(Clinic clinic, Doctor doctor, Patient patient) {
        if (doctor != null && doctor.getClinic().getId().equals(clinic.getId())) {
            doctor.setClinic(null);
            doctorRepo.save(doctor);
        }
        if (patient != null) {
            for(Doctor doc : clinic.getDoctors()){
                if(doc.getClinic().getId().equals(patient.getClinic().getId())){
                    doc.getPatients().remove(patient);
                    doctorRepo.save(doc);
                }
            }
            patient.setClinic(null);
           /* clinic.getPatients().remove(patient);*/
        }

        clinicRepo.save(clinic);
    }
}
