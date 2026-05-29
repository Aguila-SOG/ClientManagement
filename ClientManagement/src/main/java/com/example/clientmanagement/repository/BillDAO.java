package com.example.clientmanagement.repository;

import com.example.clientmanagement.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDAO extends JpaRepository<Bill, Long> {

    List<Bill> findByCustomerId(Long customerId);

    @Query("SELECT b FROM Bill b WHERE YEAR(b.billDate) = :selectedYear and MONTH(b.billDate) BETWEEN :startMonth AND :endMonth")
    List<Bill> findSpecificMonths(@Param("startMonth") int startMonth,
                                  @Param("endMonth") int endMonth,
                                  @Param("selectedYear") int year);

    @Query("SELECT b FROM Bill b WHERE YEAR(b.billDate) = :selectedYear")
    List<Bill> findSpecificMonthsYearly(@Param("selectedYear") int year);
}
