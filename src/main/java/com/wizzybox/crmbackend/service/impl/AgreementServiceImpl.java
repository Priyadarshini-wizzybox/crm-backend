package com.wizzybox.crmbackend.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wizzybox.crmbackend.model.Agreement;
import com.wizzybox.crmbackend.repository.AgreementRepository;
import com.wizzybox.crmbackend.service.AgreementService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class AgreementServiceImpl implements AgreementService {

    @Value("${file.upload-dir}")  // Get the upload directory from application.properties
    private String uploadDir;

    private final AgreementRepository agreementRepository;

    public AgreementServiceImpl(AgreementRepository agreementRepository) {
        this.agreementRepository = agreementRepository;
    }

    @Override
    public Agreement saveAgreement(Agreement agreement, MultipartFile file) throws IOException {
        // Save the uploaded file and set the file path in the Agreement entity
        String fileName = saveFile(file);
        agreement.setMsaFile("/uploads/" + fileName); // Store the relative file path

        return agreementRepository.save(agreement);
    }

    @Override
    public List<Agreement> getAllAgreements() {
        return agreementRepository.findAll();
    }

    @Override
    public Agreement getAgreementById(Long id) {
        return agreementRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAgreement(Long id) {
        agreementRepository.deleteById(id);
    }

    private String saveFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        
        // Ensure the upload path is resolved from the properties
        Path uploadPath = Paths.get(uploadDir);  // This will be the absolute path like 'C:/uploads'

        // Create the upload directory if it doesn't exist
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save the file to the specified directory
        Path filePath = uploadPath.resolve(fileName);
        file.transferTo(filePath.toFile());

        return fileName;
    }

}
