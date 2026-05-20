package com.example.clientmanagement.service;

import com.example.clientmanagement.entity.Management;
import com.example.clientmanagement.repository.ManagementDAO;
import org.springframework.stereotype.Service;

@Service
public class ManagementService {
    private final ManagementDAO managementDAO;

    public ManagementService(ManagementDAO managementDAO){
        this.managementDAO = managementDAO;
    }

    public Management create(Management management){
        if (management.getQuarterly() > 4 || management.getQuarterly() < 1){
            throw new IllegalArgumentException("The quarterly have to be betwen 1 and 4");
        }

    }

}
