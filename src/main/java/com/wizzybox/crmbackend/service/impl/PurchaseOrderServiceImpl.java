package com.wizzybox.crmbackend.service.impl;



import com.wizzybox.crmbackend.model.PurchaseOrder;

import com.wizzybox.crmbackend.repository.PurchaseOrderRepository;
import com.wizzybox.crmbackend.service.PurchaseOrderService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	 @Value("${file.upload-dir}")  // Get the upload directory from application.properties
	    private String uploadDir;

	    private final PurchaseOrderRepository purchaseOrderRepository;

	   

	    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository) {

			this.purchaseOrderRepository = purchaseOrderRepository;
		}
		@Override
	    public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder, MultipartFile file) throws IOException {
// Save the uploaded file and set the file path in the Agreement entity
	        String fileName = saveFile(file);
	        purchaseOrder.setFileUpload("/uploads/" + fileName); // Store the relative file path

	        return purchaseOrderRepository.save(purchaseOrder);
	    }
    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public PurchaseOrder getPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id).orElse(null);
    }
   

    @Override
    public void deletePurchaseOrder(Long id) {
        purchaseOrderRepository.deleteById(id);
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
