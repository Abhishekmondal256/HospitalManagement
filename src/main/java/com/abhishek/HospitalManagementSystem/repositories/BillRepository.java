package com.abhishek.HospitalManagementSystem.repositories;

import com.abhishek.HospitalManagementSystem.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
}
