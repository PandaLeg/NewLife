package com.newLife.repo;

import com.newLife.domain.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClinicRepo extends JpaRepository<Clinic, Long> {
    Clinic findByUsername(String username);
    @Query(value = "SELECT clinic_id FROM clinic_doctors cd where cd.doctor_id = :id", nativeQuery = true)
    Long findByClinic_IdFromDoctor_Id(long id);
    @Query(value = "SELECT clinic_id FROM clinic_patients cp where cp.patient_id = :id", nativeQuery = true)
    Long findByClinic_IdFromPatient_Id(long id);
}
