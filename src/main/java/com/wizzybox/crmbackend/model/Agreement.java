package com.wizzybox.crmbackend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "agreement") 
public class Agreement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;
    private String employeeName;
    private String employeeId;
    private LocalDate startDate;
    private LocalDate endDate;

    // These fields will store the file paths for MSA and NDA
    private String msaFile;
    private String ndaFile;

}