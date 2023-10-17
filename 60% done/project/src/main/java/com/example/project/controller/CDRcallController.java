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

import com.example.project.business.CdrCallForm;
import com.example.project.business.LoginBody;
import com.example.project.business.Status;
import com.example.project.service.CdrCallService;
import com.example.project.service.UserService;
// import com.talentsprint.cycleshop.service.CartService;
// import com.talentsprint.cycleshop.service.CycleService;
// import com.talentsprint.cycleshop.service.DomainUserService;
// import com.talentsprint.cycleshop.service.RegistrationForm;
// import com.talentsprint.cycleshop.service.TransactionService;

@RestController
@CrossOrigin
@RequestMapping("/api/home")
public class CDRcallController {

    private LoginBody loginBody;

    @Autowired
    private UserService userService;

    @Autowired
    private CdrCallService cdrCallService;

    @GetMapping("/list-calldata")
    public ResponseEntity<Object> listCdrFiles(Authentication authentication) {
        System.out.println("Get Request");
        return ResponseEntity.ok(cdrCallService.listCallCdrs());

    }

    @PostMapping("/saveCallCDR")
    public Status saveCDRCallData(@RequestBody CdrCallForm form) {
        System.out.println("This is controller");
        Status status = cdrCallService.saveCDRCallData(form);
        return status;
    }

    @PostMapping("/saveCallCDRtoCSV")
    public Status saveCDRtoCSV(@RequestBody CdrCallForm form) {
        Status status = cdrCallService.saveCDRCallData(form);
        return status;

    }
}
