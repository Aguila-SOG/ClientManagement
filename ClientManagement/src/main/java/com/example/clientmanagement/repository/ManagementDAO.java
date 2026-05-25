package com.example.clientmanagement.repository;

import com.example.clientmanagement.entity.Management;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ManagementDAO extends JpaRepository<Management, Long> {

    List<Management> findByFacYear(Integer facYear);

    Management findByFacYearAndQuarterly(Integer facYear, Integer quarterly);
}