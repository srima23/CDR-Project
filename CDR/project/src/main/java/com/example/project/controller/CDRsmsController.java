
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
    public ResponseEntity<StatusClass> saveCDRSmsData(@RequestBody CdrSmsForm form) {
        return ResponseEntity.ok(cdrSmsService.saveCDRSmsData(form));
    }

    @PostMapping("/saveSmsCDRtoCSV")
    public ResponseEntity<StatusClass> saveCDRtoCSV(@RequestBody CdrSmsForm form) {
        return ResponseEntity.ok(cdrSmsService.saveCDRSmsData(form));
    }

    @PostMapping("/smsupload")
    public ResponseEntity<StatusClass> uploadSmsCSVFile(@RequestParam("file") MultipartFile file) {
        StatusClass status = new StatusClass();
        if (file != null && !file.isEmpty()) {
            try {
                // Process the CSV file and store it in the database
                cdrSmsService.processCSVFile(file);
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
