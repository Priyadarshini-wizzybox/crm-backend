package com.wizzybox.crmbackend.repository;

import com.wizzybox.crmbackend.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}
