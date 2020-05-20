package com.newLife.repo;

import com.newLife.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepo extends JpaRepository<Request, Long> {
    List<Request> findByClinic_Id(Long id);
    List<Request> findByDoctor_Id(Long id);
}
