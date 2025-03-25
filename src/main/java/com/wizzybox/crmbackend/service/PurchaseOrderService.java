package com.wizzybox.crmbackend.service;



import com.wizzybox.crmbackend.model.PurchaseOrder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface PurchaseOrderService {

    PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder, MultipartFile file) throws IOException;

    List<PurchaseOrder> getAllPurchaseOrders();

    PurchaseOrder getPurchaseOrderById(Long id);

    void deletePurchaseOrder(Long id);
}
