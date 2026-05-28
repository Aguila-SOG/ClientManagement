package com.example.clientmanagement.service;

import com.example.clientmanagement.entity.Management;
import com.example.clientmanagement.repository.ManagementDAO;
import org.springframework.stereotype.Service;
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
        managementDAO.save(management);
    }

    public Management findById(int year, int quarterly) {
        return managementDAO.findById(new Management.Id(year, quarterly))
                .orElseThrow(() -> new RuntimeException("Management record not found"));
    }

    public Management editManagement(int year, int quarterly, Management management) {
        Management existing = findById(year, quarterly);
        existing.setTaxPayment(management.getTaxPayment());
        existing.setPerformance(management.getPerformance());
        return managementDAO.save(existing);
    }

    public void deleteManagement(int year, int quarterly) {
        findById(year, quarterly);
        managementDAO.deleteById(new Management.Id(year, quarterly));
    }
}