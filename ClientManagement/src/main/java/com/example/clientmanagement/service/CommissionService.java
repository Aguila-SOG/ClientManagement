package com.example.clientmanagement.service;

import com.example.clientmanagement.entity.Commission;
import com.example.clientmanagement.repository.CommissionDAO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommissionService {

    private final CommissionDAO commissionDAO;

    public CommissionService(CommissionDAO commissionDAO) {
        this.commissionDAO = commissionDAO;
    }

    public List<Commission> findAll() {
        return commissionDAO.findAll();
    }

    public List<Commission> findAllByClient(Long customerId) {
        return commissionDAO.findByCustomerId(customerId);
    }

    public Commission findCommissionById(Long id) {
        return commissionDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("The bill with the id: '"+id+"' does not exist"));
    }

    public Commission create(Commission commission) {
        if (commission.getBill() == null || commission.getBill().getIdNumber() == null) {
            throw new IllegalArgumentException("The commission must be associated with a bill");
        }
        if (commission.getCustomer() == null || commission.getCustomer().getId() == null) {
            throw new IllegalArgumentException("The commission must be associated with a customer");
        }
        return commissionDAO.save(commission);
    }

    public Commission editCommission(Commission commission) {
        return commissionDAO.save(commission);
    }

    public void deleteCommission(Long id) {
        commissionDAO.deleteById(id);
    }
}
