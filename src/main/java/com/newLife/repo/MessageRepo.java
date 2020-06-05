package com.newLife.repo;

import com.newLife.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
    List<Message> findByPatient_Id(Long id);
}
