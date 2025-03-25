package com.wizzybox.crmbackend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.wizzybox.crmbackend.model.Agreement;
import com.wizzybox.crmbackend.service.AgreementService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/agreements")
public class AgreementController {

    private static final Logger logger = LoggerFactory.getLogger(AgreementController.class);

    @Autowired
    private AgreementService agreementService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadAgreement(@RequestParam("fileUpload") MultipartFile file,
                                                  @RequestParam String clientName,
                                                  @RequestParam String employeeName,
                                                  @RequestParam String employeeId,
                                                  @RequestParam String startDate,
                                                  @RequestParam String endDate) {
        try {
            Agreement agreement = new Agreement();
            agreement.setClientName(clientName);
            agreement.setEmployeeName(employeeName);
            agreement.setEmployeeId(employeeId);
           
            agreement.setStartDate(LocalDate.parse(startDate));
            agreement.setEndDate(LocalDate.parse(endDate));

            // Save the agreement and uploaded file
            agreementService.saveAgreement(agreement, file);

            logger.info("Agreement uploaded successfully: {}", agreement);

            return ResponseEntity.status(HttpStatus.CREATED).body("Agreement uploaded successfully!");

        } catch (IOException e) {
            logger.error("File upload failed due to an IOException", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());

        } catch (Exception e) {
            logger.error("Unexpected error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Agreement> getAllAgreements() {
        return agreementService.getAllAgreements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agreement> getAgreementById(@PathVariable Long id) {
        Agreement agreement = agreementService.getAgreementById(id);
        return agreement != null ? ResponseEntity.ok(agreement) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgreement(@PathVariable Long id) {
        agreementService.deleteAgreement(id);
        return ResponseEntity.noContent().build();
    }
}
