package com.wizzybox.crmbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wizzybox.crmbackend.model.Agreement;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {

}
