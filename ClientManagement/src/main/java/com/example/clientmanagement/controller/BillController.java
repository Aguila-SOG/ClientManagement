package com.example.clientmanagement.controller;

import com.example.clientmanagement.entity.Bill;
import com.example.clientmanagement.service.BillService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/bill")
@Tag(name="Facturas", description = "Operaciones de los pagos de los clientes")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    @Operation(summary = "Busca todas las facturas")
    public ResponseEntity<List<Bill>> findAll() throws SQLException {
        return ResponseEntity.ok(billService.findAll());
    }

    @GetMapping("/customer/{id_customer}")
    @Operation(summary = "Busca todas las facturas de un cliente")
    public ResponseEntity<List<Bill>> findAllByClient(@PathVariable("id_customer") long id) throws SQLException {
        return ResponseEntity.ok(billService.findAllByClient(id));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca todas las facturas mediante el ID del cliente")
    public ResponseEntity<Bill> findBillById(@PathVariable Long id) throws SQLException, ResponseStatusException {
        Bill bill = billService.findBillById(id);
        if (bill == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bill with id: '"+id+"' does not exist");
        }
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary= "Crea una factura")
    public ResponseEntity<Bill> create(@RequestBody Bill bill) throws SQLException {
        billService.create(bill);
        return ResponseEntity.created(URI.create("/bill/"+bill.getIdNumber())).body(bill);
    }

    @PutMapping
    @Operation(summary = "Edita ua factura")
    public ResponseEntity<Bill> editBill(@RequestBody Bill bill) throws SQLException {
        Bill edited = billService.editBill(bill);
        return ResponseEntity.ok(edited);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "elimina una factura")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) throws SQLException {
        billService.deleteBill(id);
        return ResponseEntity.noContent().build();
    }
}
