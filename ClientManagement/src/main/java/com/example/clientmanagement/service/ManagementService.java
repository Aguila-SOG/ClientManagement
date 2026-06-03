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

    public Management findById(int year, int quarterly, int month) {
        return managementDAO.findById(new Management.Id(year, quarterly, month))
                .orElseThrow(() -> new RuntimeException("Management record not found"));
    }

    public Management editManagement(int year, int quarterly, int month, Management management) {
        Management existing = findById(year, quarterly, month);
        existing.setTaxPayment(management.getTaxPayment());
        existing.setPerformance(management.getPerformance());
        return managementDAO.save(existing);
    }

    public void deleteManagement(int year, int quarterly, int month) {
        findById(year, quarterly, month);
        managementDAO.deleteById(new Management.Id(year, quarterly, month));
    }
}