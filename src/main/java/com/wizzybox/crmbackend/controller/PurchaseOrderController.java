package com.wizzybox.crmbackend.controller;

import com.wizzybox.crmbackend.model.PurchaseOrder;
import com.wizzybox.crmbackend.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    // POST method to upload a purchase order with file
    @PostMapping("/upload")
    public ResponseEntity<String> uploadPurchaseOrder(@RequestParam("fileUpload") MultipartFile file,
                                                      @RequestParam String clientName,
                                                      @RequestParam String employeeName,
                                                      @RequestParam String employeeId,
                                                      @RequestParam String poNumber,
                                                      @RequestParam String startDate,
                                                      @RequestParam String endDate) {
        try {
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setClientName(clientName);
            purchaseOrder.setEmployeeName(employeeName);
            purchaseOrder.setEmployeeId(employeeId);
            purchaseOrder.setPoNumber(poNumber);
            purchaseOrder.setStartDate(LocalDate.parse(startDate));
            purchaseOrder.setEndDate(LocalDate.parse(endDate));

            // Save the purchase order along with the file
            purchaseOrderService.savePurchaseOrder(purchaseOrder, file);

            return ResponseEntity.status(HttpStatus.CREATED).body("Purchase order uploaded successfully!");

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());
        }
    }

    // GET method to retrieve all purchase orders
    @GetMapping
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderService.getAllPurchaseOrders();
    }

    // GET method to retrieve a purchase order by ID
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getPurchaseOrderById(@PathVariable Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderService.getPurchaseOrderById(id);
        return purchaseOrder !=null ? ResponseEntity.ok(purchaseOrder):ResponseEntity.notFound().build();
    }
   

    // DELETE method to delete a purchase order by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchaseOrder(@PathVariable Long id) {
        purchaseOrderService.deletePurchaseOrder(id);
        return ResponseEntity.noContent().build();
    }
}
