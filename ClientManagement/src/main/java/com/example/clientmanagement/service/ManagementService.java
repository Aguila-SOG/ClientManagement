package com.example.clientmanagement.service;

import com.example.clientmanagement.entity.Management;
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
        if (management.getFac_year() == null){
            throw new IllegalArgumentException("Fac_year is required");
        }
        if (management.getTax_payment() == null){
            throw new IllegalArgumentException("Tax payment is required");
        }
        if (management.getPerformance() == null){
            throw new IllegalArgumentException("Performance is required");
        }
        management.setQuarterly(calculateQuarter());

        managementDAO.createManagement(management);
    }

    public Management findById(Long id) {
        return managementDAO.findManagement(id);
    }

    public Management update(Long id, Management management) {
        return managementDAO.editManagement(
                id,
                management.getFac_year(),
                management.getQuarterly(),
                management.getTax_payment(),
                management.getPerformance()
        );
    }

    public void delete(Long id) {
        managementDAO.deleteManagement(id);
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