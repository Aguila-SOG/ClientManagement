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

    public Management findById(Long id) {
        return managementDAO.findById(id).orElseThrow(() -> new RuntimeException("Management not found"));
    }

    public Management createManagement(Management management){
        return managementDAO.save(management);
    }

    public Management update(Long id, Management management) {
        Management existing = findById(id);

        existing.setFac_year(management.getFac_year());
        existing.setQuarterly(management.getQuarterly());
        existing.setTax_payment(management.getTax_payment());
        existing.setPerformance(management.getPerformance());

        return managementDAO.save(existing);
    }

    public void delete(Long id) {
        if (!managementDAO.existsById(id)) {
            throw new RuntimeException("Management not found.");
        }
        managementDAO.deleteById(id);
    }
}