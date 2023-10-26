package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.business.CustomersForm;
import com.example.project.business.StatusClass;
import com.example.project.service.CustomersService;

@RestController
@CrossOrigin
@RequestMapping("/api/home")
public class CustomersController {

    @Autowired
    public CustomersService customersService;

    @PostMapping("/saveCustomers")
    public ResponseEntity<StatusClass> saveCustomersDetails(@RequestBody CustomersForm form) {
        return ResponseEntity.ok(customersService.saveCustomersDetails(form));
    }

    @PostMapping("/saveCustomerCDRtoCSV")
    public ResponseEntity<StatusClass> saveCDRtoCSV(@RequestBody CustomersForm form) {
        return ResponseEntity.ok(customersService.saveCustomersDetails(form));
    }
}
