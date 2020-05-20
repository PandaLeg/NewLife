package com.newLife.repo;

import com.newLife.domain.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClinicRepo extends JpaRepository<Clinic, Long> {
    Clinic findByUsername(String username);
    @Query(value = "SELECT clinic_id FROM clinic_doctors cd where cd.doctor_id = :username", nativeQuery = true)
    Long findByClinic_Id(Long username);
}
