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
        return commissionDAO.findAllByClient(customerId);
    }

    public Commission findCommission(Long id) {
        Commission commission = commissionDAO.findCommissionById(id);
        if (commission == null) {
            throw new EntityNotFoundException("The commission with id: '"+id+"' does not exist");
        }
        return commission;
    }

    public Commission create(Commission commission) {
        if (commission.getBill() == null || commission.getBill().getIdNumber() == null) {
            throw new IllegalArgumentException("The commission must be associated with a bill");
        }
        if (commission.getCustomer() == null || commission.getCustomer().getId() == null) {
            throw new IllegalArgumentException("The commission must be associated with a customer");
        }
        return commissionDAO.create(commission);
    }

    public Commission editCommission(Commission commission) {
        return commissionDAO.editCommission(commission);
    }

    public void deleteCommission(Long id) {
        commissionDAO.deleteCommission(id);
    }
}
