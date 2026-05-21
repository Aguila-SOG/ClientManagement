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
    public Management findById(@PathVariable Long id) {
        return managementService.findById(id);
    }

    @PutMapping("/{id}")
    public Management update(
            @PathVariable Long id,
            @RequestBody Management management) {

        return managementService.update(id, management);
    }

    @PostMapping
    public void create(@RequestBody Management management) {
        managementService.createManagement(management);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        managementService.delete(id);
    }
}
