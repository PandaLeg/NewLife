package com.newLife.repo;

import com.newLife.domain.Clinic;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClinicRepo extends JpaRepository<Clinic, Long> {
    Clinic findByUsername(String username);

    @Query(value = "SELECT clinic_id FROM clinic_patients cp where cp.patient_id = :id", nativeQuery = true)
    Long findByClinic_IdFromPatient_Id(long id);
}
