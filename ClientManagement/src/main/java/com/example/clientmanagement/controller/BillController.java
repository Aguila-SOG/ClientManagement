package com.example.clientmanagement.controller;

import com.example.clientmanagement.entity.Bill;
import com.example.clientmanagement.service.BillService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/bill")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public ResponseEntity<List<Bill>> findAll() {
        return ResponseEntity.ok(billService.findAll());
    }

    @GetMapping("/customer/{idCustomer}")
    public ResponseEntity<List<Bill>> findAllByClient(@PathVariable("idCustomer") long id) {
        return ResponseEntity.ok(billService.findAllByClient(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> findBillById(@PathVariable Long id) {
        Bill bill = billService.findBillById(id);
        if (bill == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bill with id: '"+id+"' does not exist");
        }
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Bill> create(@RequestBody Bill bill) {
        billService.create(bill);
        return ResponseEntity.created(URI.create("/bill/"+bill.getIdNumber())).body(bill);
    }

    @PutMapping
    public ResponseEntity<Bill> editBill(@RequestBody Bill bill) {
        Bill edited = billService.editBill(bill);
        return ResponseEntity.ok(edited);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
        return ResponseEntity.noContent().build();
    }
}
