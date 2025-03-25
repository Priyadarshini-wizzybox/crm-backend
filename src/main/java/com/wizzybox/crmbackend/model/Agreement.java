package com.wizzybox.crmbackend.model;

import jakarta.persistence.Entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

	@Entity
	@Data
	public class Agreement {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String clientName;
	    private String employeeName;
	    private String employeeId;
	    @Enumerated(EnumType.STRING)
	    private AgreementType type;
	    
	    private LocalDate startDate;
	    private LocalDate endDate;

	    private String fileUpload; // Store the file path

		
}