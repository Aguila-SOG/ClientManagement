package com.example.clientmanagement.controller;

import com.example.clientmanagement.entity.Bill;
import com.example.clientmanagement.entity.Commission;
import com.example.clientmanagement.service.CommissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/commission")
public class CommissionController {

    private final CommissionService commissionService;

    public CommissionController(CommissionService commissionService) {
        this.commissionService = commissionService;
    }

    @GetMapping
    public ResponseEntity<List<Commission>> findAll() {
        return ResponseEntity.ok(commissionService.findAll());
    }

    @GetMapping("/customer/{id_customer}")
    public ResponseEntity<List<Commission>> findAllByClient(@PathVariable("id_customer") long id) {
        return ResponseEntity.ok(commissionService.findAllByClient(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commission> findCommissionById(@PathVariable Long id) {
        Commission commission = commissionService.findCommission(id);

        return new ResponseEntity<>(commission, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Commission> create(@RequestBody Commission commission) {
        commissionService.create(commission);
        return ResponseEntity.created(URI.create("/commission/"+commission.getId())).body(commission);
    }

    @PutMapping
    public ResponseEntity<Commission> editCommission(@RequestBody Commission commission) {
        Commission edited = commissionService.editCommission(commission);
        return ResponseEntity.ok(edited);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommission(@PathVariable Long id) {
        commissionService.deleteCommission(id);
        return ResponseEntity.noContent().build();
    }
}
