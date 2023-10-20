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
import com.example.project.business.StatusClass;
import com.example.project.service.CdrCallService;

@RestController
@CrossOrigin
@RequestMapping("/api/home")
public class CDRcallController {

    @Autowired
    private CdrCallService cdrCallService;

    @GetMapping("/list-calldata")
    public ResponseEntity<Object> listCdrFiles(Authentication authentication) {
        return ResponseEntity.ok(cdrCallService.listCallCdrs());

    }

    @PostMapping("/saveCallCDR")
    public StatusClass saveCDRCallData(@RequestBody CdrCallForm form) {
        System.out.println("controller");
        StatusClass status = cdrCallService.saveCDRCallData(form);
        return status;
    }

    @PostMapping("/saveCallCDRtoCSV")
    public StatusClass saveCDRtoCSV(@RequestBody CdrCallForm form) {
        StatusClass status = cdrCallService.saveCDRCallData(form);
        return status;

    }
}
