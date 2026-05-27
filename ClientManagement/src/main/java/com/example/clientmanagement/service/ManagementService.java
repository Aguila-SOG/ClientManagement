package com.example.clientmanagement.service;

import com.example.clientmanagement.entity.Management;
import com.example.clientmanagement.entity.ManagementId; // <-- Importamos la nueva clave compuesta
import com.example.clientmanagement.repository.ManagementDAO;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
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
        if (management.getTaxPayment() == null){
            throw new IllegalArgumentException("Tax payment is required");
        }
        if (management.getPerformance() == null){
            throw new IllegalArgumentException("Performance is required");
        }
        management.setQuarterly(calculateQuarter());
        managementDAO.save(management);
    }

    public Management findManagement(int year, int quarterly) {
        return managementDAO.findById(new ManagementId(year, quarterly))
                .orElseThrow(() -> new RuntimeException("Management record not found"));
    }

    public Management editManagement(int year, int quarterly, Management management) {
        Management existing = findManagement(year, quarterly);

        if (management.getTaxPayment() != null) {
            existing.setTaxPayment(management.getTaxPayment());
        }
        if (management.getPerformance() != null) {
            existing.setPerformance(management.getPerformance());
        }

        return managementDAO.save(existing);
    }

    public void deleteManagement(int year, int quarterly) {
        findManagement(year, quarterly);
        managementDAO.deleteById(new ManagementId(year, quarterly));
    }

    private int calculateQuarter(){
        int month = LocalDate.now().getMonthValue();
        if(month >= 1 && month <= 3){
            return 1;
        } else if(month >= 4 && month <= 6){
            return 2;
        } else if(month >= 7 && month <= 9){
            return 3;
        } else {
            return 4;
        }
    }
}