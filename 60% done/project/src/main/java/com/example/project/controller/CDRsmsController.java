
package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.business.CdrSmsForm;
import com.example.project.business.LoginBody;
import com.example.project.business.Status;
import com.example.project.service.CdrSmsService;
import com.example.project.service.UserService;
// import com.talentsprint.cycleshop.service.CartService;
// import com.talentsprint.cycleshop.service.CycleService;
// import com.talentsprint.cycleshop.service.DomainUserService;
// import com.talentsprint.cycleshop.service.RegistrationForm;
// import com.talentsprint.cycleshop.service.TransactionService;

@RestController
@CrossOrigin
@RequestMapping("/api/home")
public class CDRsmsController {

    private LoginBody loginBody;

    @Autowired
    private UserService userService;

    @Autowired
    private CdrSmsService cdrSmsService;

    @GetMapping("/list-smsdata")
    public ResponseEntity<Object> listCdrFiles(Authentication authentication) {
        System.out.println("Get Request");
        return ResponseEntity.ok(cdrSmsService.listSmsCdrs());

    }

    @PostMapping("/saveSmsCDR")
    public Status saveCDRSmsData(@RequestBody CdrSmsForm form) {
        System.out.println("This is controller");
        Status status = cdrSmsService.saveCDRSmsData(form);
        return status;
    }

    @PostMapping("/saveSmsCDRtoCSV")
    public Status saveCDRtoCSV(@RequestBody CdrSmsForm form) {
        System.out.println("this ");
        Status status = cdrSmsService.saveCDRSmsData(form);
        return status;

    }

}
