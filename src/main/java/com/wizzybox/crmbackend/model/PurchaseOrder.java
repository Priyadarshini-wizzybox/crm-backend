package com.wizzybox.crmbackend.model;


import lombok.Data;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Data
@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName; 

    private String employeeName; 

    private String employeeId; 

    private String poNumber;

    private LocalDate startDate; 

    private LocalDate endDate; 

    private String fileUpload; 

}