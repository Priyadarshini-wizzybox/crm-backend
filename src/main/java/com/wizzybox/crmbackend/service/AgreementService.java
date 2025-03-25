package com.wizzybox.crmbackend.service;

import org.springframework.web.multipart.MultipartFile;


import com.wizzybox.crmbackend.model.Agreement;


import java.io.IOException;
import java.util.List;

public interface AgreementService {

	Agreement saveAgreement(Agreement agreement, MultipartFile file) throws IOException;
	
    List<Agreement> getAllAgreements();

    Agreement getAgreementById(Long id);

    void deleteAgreement(Long id);
}
