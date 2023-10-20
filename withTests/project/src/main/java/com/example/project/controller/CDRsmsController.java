
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
import com.example.project.business.StatusClass;
import com.example.project.service.CdrSmsService;

@RestController
@CrossOrigin
@RequestMapping("/api/home")
public class CDRsmsController {

    @Autowired
    private CdrSmsService cdrSmsService;

    @GetMapping("/list-smsdata")
    public ResponseEntity<Object> listCdrFiles(Authentication authentication) {
        return ResponseEntity.ok(cdrSmsService.listSmsCdrs());

    }

    @PostMapping("/saveSmsCDR")
    public StatusClass saveCDRSmsData(@RequestBody CdrSmsForm form) {
        StatusClass status = cdrSmsService.saveCDRSmsData(form);
        return status;
    }

    @PostMapping("/saveSmsCDRtoCSV")
    public StatusClass saveCDRtoCSV(@RequestBody CdrSmsForm form) {
        StatusClass status = cdrSmsService.saveCDRSmsData(form);
        return status;

    }

}
