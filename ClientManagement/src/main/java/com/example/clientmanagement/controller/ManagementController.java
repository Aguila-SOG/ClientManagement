package com.example.clientmanagement.controller;

import com.example.clientmanagement.entity.Management;
import com.example.clientmanagement.service.ManagementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins="*")
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

    @GetMapping("/{facYear}/{quarterly}")
    public Management findById(@PathVariable int facYear, @PathVariable int quarterly) {
        return managementService.findManagement(facYear, quarterly);
    }

    @PostMapping("/create")
    public Management create(@RequestBody Management management) {
        managementService.createManagement(management);
        return management;
    }

    @PutMapping("/edit/{facYear}/{quarterly}")
    public Management update(
            @PathVariable int facYear,
            @PathVariable int quarterly,
            @RequestBody Management management) {
        return managementService.editManagement(facYear, quarterly, management);
    }

    @DeleteMapping("/delete/{facYear}/{quarterly}")
    public void delete(@PathVariable int facYear, @PathVariable int quarterly) {
        managementService.deleteManagement(facYear, quarterly);
    }
}