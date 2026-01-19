//package com.abhishek.HospitalManagementSystem.services;
//
//import com.abhishek.HospitalManagementSystem.models.Bill;
//import com.abhishek.HospitalManagementSystem.repositories.BillRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class BillService {
//
//    @Autowired
//    private BillRepository billRepository;
//
//    public List<Bill> getAllBills() {
//        return billRepository.findAll();
//    }
//
//    public Bill createBill(Bill bill) {
//        return billRepository.save(bill);
//    }
//
//    public Bill getBillById(Long id) {
//        return billRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Bill not found with id " + id));
//    }
//
//    public void deleteBill(Long id) {
//        billRepository.deleteById(id);
//    }
//
//    public Bill updateBill(Long id, Bill updatedBill) {
//        Bill existing = getBillById(id);
//        existing.setPatientId(updatedBill.getPatientId());
//        existing.setAmount(updatedBill.getAmount());
//        existing.setStatus(updatedBill.getStatus());
//        return billRepository.save(existing);
//    }
//}
