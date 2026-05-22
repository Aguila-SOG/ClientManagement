package com.example.clientmanagement.controller;

import com.example.clientmanagement.entity.Management;
import com.example.clientmanagement.service.ManagementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management")
public class ManagementController {
    private final ManagementService managementService;

    public ManagementController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @GetMapping
    public List<Management> findAll(){
        return managementService.findAll();
    }

    @GetMapping("/{id}")
    public Management findManagement(@PathVariable int year, @PathVariable int quarterly) {
        return managementService.findManagement(year, quarterly);
    }

    @PutMapping("/{id}")
    public Management editManagement(
            @RequestBody Management management) {

        return managementService.editManagement(management);
    }

    @PostMapping
    public void create(@RequestBody Management management) {
        managementService.createManagement(management);
    }

    @DeleteMapping("/{id}")
    public void deleteManagement(@PathVariable int year, @PathVariable int quarterly) {
        managementService.deleteManagement(year, quarterly);
    }
}
