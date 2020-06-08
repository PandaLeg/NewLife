package com.newLife.repo;

import com.newLife.domain.Patient;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient, Long> {
    Patient findByUsername(String username);
}
