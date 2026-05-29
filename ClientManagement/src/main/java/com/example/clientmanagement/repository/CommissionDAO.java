package com.example.clientmanagement.repository;

import com.example.clientmanagement.entity.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommissionDAO extends JpaRepository<Commission, Long> {

    List<Commission> findByCustomerId(Long id);
}
