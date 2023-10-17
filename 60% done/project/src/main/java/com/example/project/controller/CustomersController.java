package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.business.CustomersForm;
import com.example.project.business.LoginBody;
import com.example.project.business.Status;
import com.example.project.service.CustomersService;
// import com.talentsprint.cycleshop.service.CartService;
// import com.talentsprint.cycleshop.service.CycleService;
// import com.talentsprint.cycleshop.service.DomainUserService;
// import com.talentsprint.cycleshop.service.RegistrationForm;
// import com.talentsprint.cycleshop.service.TransactionService;
import com.example.project.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/home")
public class CustomersController {

    private LoginBody loginBody;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomersService customersService;

    // @GetMapping("/list-customers")
    // public ResponseEntity<Iterable<Customers>> listCustomersFiles() {
    // System.out.println("Get Request");
    // return ResponseEntity.ok(CustomersService.listCustomers());

    // }

    @PostMapping("/saveCustomers")
    public Status saveCustomersData(@RequestBody CustomersForm form) {
        System.out.println("This is controller");
        Status status = customersService.saveCustomersData(form);
        return status;
    }

    @PostMapping("/saveCustomerCDRtoCSV")
    public Status saveCDRtoCSV(@RequestBody CustomersForm form) {
        Status status = customersService.saveCustomersData(form);
        return status;

    }
}
