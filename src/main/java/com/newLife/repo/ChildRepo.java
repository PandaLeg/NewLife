package com.newLife.repo;

import com.newLife.domain.Child;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildRepo extends JpaRepository<Child, Long> {
    List<Child> findByPatient_Id(long id);
}
