package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<StatusClass> saveCDRCallData(@RequestBody CdrCallForm form) {
        return ResponseEntity.ok(cdrCallService.saveCDRCallData(form));
    }

    @PostMapping("/saveCallCDRtoCSV")
    public ResponseEntity<StatusClass> saveCDRtoCSV(@RequestBody CdrCallForm form) {
        return ResponseEntity.ok(cdrCallService.saveCDRCallData(form));
    }

    @PostMapping("/callupload")
    public ResponseEntity<StatusClass> uploadCallCSVFile(@RequestParam("file") MultipartFile file) {
        StatusClass status = new StatusClass();
        if (file != null && !file.isEmpty()) {
            try {
                // Process the CSV file and store it in the database
                cdrCallService.processCSVFile(file);
                status.setStatus("uploaded successfully");
                return ResponseEntity.ok(status);
            } catch (Exception e) {
                status.setStatus("error: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
            }
        } else {
            status.setStatus("error: No file uploaded");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(status);
        }
    }

}
