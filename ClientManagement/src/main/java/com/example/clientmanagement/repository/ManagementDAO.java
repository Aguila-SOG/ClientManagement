package com.example.clientmanagement.repository;

import com.example.clientmanagement.entity.Management;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagementDAO extends JpaRepository<Management, Management.Id> {

}