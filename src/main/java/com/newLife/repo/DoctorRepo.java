package com.newLife.repo;

import com.newLife.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    Doctor findByUsername(String username);
}
