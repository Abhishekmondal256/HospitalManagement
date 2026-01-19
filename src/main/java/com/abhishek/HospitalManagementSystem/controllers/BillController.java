//package com.abhishek.HospitalManagementSystem.controllers;
//
//import com.abhishek.HospitalManagementSystem.services.BillService;
//import com.abhishek.HospitalManagementSystem.models.Bill;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/bills")
//public class BillController {
//
//    @Autowired
//    private BillService billService;
//
//    @GetMapping
//    public List<Bill> getAllBills() {
//        return billService.getAllBills();
//    }
//
//    @PostMapping
//    public Bill createBill(@RequestBody Bill bill) {
//        return billService.createBill(bill);
//    }
//
//    @GetMapping("/{id}")
//    public Bill getBillById(@PathVariable Long id) {
//        return billService.getBillById(id);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteBill(@PathVariable Long id) {
//        billService.deleteBill(id);
//    }
//
//    @PutMapping("/{id}")
//    public Bill updateBill(@PathVariable Long id, @RequestBody Bill bill) {
//        return billService.updateBill(id, bill);
//    }
//}
