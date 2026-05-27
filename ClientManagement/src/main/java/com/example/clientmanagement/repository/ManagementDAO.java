package com.example.clientmanagement.repository;

import com.example.clientmanagement.entity.Management;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ManagementDAO extends JpaRepository<Management, Management> {

    List<Management> findByFacYear(Integer facYear);

    Management findByFacYearAndQuarterly(Integer facYear, Integer quarterly);

    @Modifying
    @Query("UPDATE Management m SET m.taxPayment = :taxPayment, m.performance = :performance WHERE m.facYear = :facYear AND m.quarterly = :quarterly")
    int updateByFacYearAndQuarterly(
            @Param("facYear") int facYear,
            @Param("quarterly") int quarterly,
            @Param("taxPayment") Double taxPayment,
            @Param("performance") Double performance
    );
}