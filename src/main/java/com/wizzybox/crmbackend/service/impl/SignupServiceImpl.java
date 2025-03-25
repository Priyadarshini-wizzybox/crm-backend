package com.wizzybox.crmbackend.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wizzybox.crmbackend.model.Signup;
import com.wizzybox.crmbackend.repository.SignupRepository;
import com.wizzybox.crmbackend.service.SignupService;

@Service
public class SignupServiceImpl implements SignupService{
	@Autowired
private SignupRepository signupRepository;

public SignupServiceImpl(SignupRepository signupRepository) {
	super();
	this.signupRepository = signupRepository;
}

@Override
public Signup saveUser(Signup signup) {
	
	return signupRepository.save(signup);
}


}

