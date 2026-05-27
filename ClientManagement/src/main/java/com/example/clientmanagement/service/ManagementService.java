package com.example.clientmanagement.service;

import com.example.clientmanagement.entity.Management;
import com.example.clientmanagement.repository.ManagementDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ManagementService {

    private final ManagementDAO managementDAO;

    public ManagementService(ManagementDAO managementDAO){
        this.managementDAO = managementDAO;
    }

    public List<Management> findAll(){
        return managementDAO.findAll();
    }

    public void createManagement(Management management){
        if (management.getFacYear() == null){
            throw new IllegalArgumentException("Fac_year is required");
        }
        if (management.getQuarterly() == null){
            throw new IllegalArgumentException("Quarterly is required");
        }
        if (management.getTaxPayment() == null){
            throw new IllegalArgumentException("Tax payment is required");
        }
        if (management.getPerformance() == null){
            throw new IllegalArgumentException("Performance is required");
        }
        managementDAO.save(management);
    }

    public Management findManagement(int year, int quarterly) {
        Management record = managementDAO.findByFacYearAndQuarterly(year, quarterly);
        if (record == null) {
            throw new RuntimeException("Management record not found");
        }
        return record;
    }

    @Transactional
    public Management editManagement(int year, int quarterly, Management management) {
        managementDAO.updateByFacYearAndQuarterly(year, quarterly, management.getTaxPayment(), management.getPerformance());
        return findManagement(year, quarterly);
    }

    public void deleteManagement(int year, int quarterly) {
        Management existing = findManagement(year, quarterly);
        managementDAO.delete(existing);
    }
}