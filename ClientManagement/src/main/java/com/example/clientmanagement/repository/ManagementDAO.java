package com.example.clientmanagement.repository;

import com.example.clientmanagement.entity.Management;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ManagementDAO extends JpaRepository<Management, Long> {

    List<Management> findByFac_year(Integer fac_year);

    Management findByFac_yearAndQuarterly(Integer fac_year, Integer quarterly);
}