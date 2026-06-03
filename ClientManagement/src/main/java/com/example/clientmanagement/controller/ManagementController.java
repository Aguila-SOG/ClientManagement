package com.example.clientmanagement.controller;

import com.example.clientmanagement.entity.Management;
import com.example.clientmanagement.service.ManagementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/management")
public class ManagementController {

    private final ManagementService managementService;

    public ManagementController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @GetMapping("/all")
    public List<Management> findAll(){
        return managementService.findAll();
    }

    @GetMapping("/{facYear}/{quarterly}/{month}")
    public Management findById(@PathVariable int facYear, @PathVariable int quarterly) {
        try {
            return managementService.findById(facYear, quarterly);
        } catch (RuntimeException exceptionNotFound) {
            return null;
        }
    }

    @PostMapping("/create")
    public Management create(@RequestBody Management management) {
        try {
            managementService.createManagement(management);
            return management;
        } catch (IllegalArgumentException exceptionDataNotFound) {
            return null;
        }
    }

    @PutMapping("/edit/{facYear}/{quarterly}/{month}")
    public Management update(
            @PathVariable int facYear,
            @PathVariable int quarterly,
            @PathVariable int month,
            @RequestBody Management management) {
        return managementService.editManagement(facYear, quarterly, month, management);
    }

    @DeleteMapping("/delete/{facYear}/{quarterly}/{month}")
    public void delete(@PathVariable int facYear, @PathVariable int quarterly) {
        managementService.deleteManagement(facYear, quarterly);
    }
}