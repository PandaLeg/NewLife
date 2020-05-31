package com.newLife.repo;

import com.newLife.domain.Doctor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    Doctor findByUsername(String username);

    @Query(value = "SELECT doctor_id FROM doctors_patients dp where dp.patient_id = :id", nativeQuery = true)
    List<Doctor> findByDoctor_Id(@Param("id")Long id);
    @Query(value = "SELECT doctor_id FROM doctors_patients dp where dp.patient_id = :id", nativeQuery = true)
    Doctor findByDoctor(@Param("id")Long id);
}
