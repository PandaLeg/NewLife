package com.newLife.repo;

import com.newLife.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    Doctor findByUsername(String username);
    @Query(value = "SELECT doctor_id FROM doctors_patients dp where dp.patient_id = :id", nativeQuery = true)
    List<Doctor> findByDoctor_Id(long id);
}
