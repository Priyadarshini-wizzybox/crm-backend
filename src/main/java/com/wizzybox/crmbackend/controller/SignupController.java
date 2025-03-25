package com.wizzybox.crmbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wizzybox.crmbackend.model.Signup;
import com.wizzybox.crmbackend.service.SignupService;

@RestController
@RequestMapping("/api/users")
public class SignupController {
private SignupService signupService;

public SignupController(SignupService signupservice) {
	super();
	this.signupService = signupservice;
}
@PostMapping()
public ResponseEntity<Signup>saveUser(@RequestBody Signup signup){
	return new ResponseEntity<Signup>(signupService.saveUser(signup),HttpStatus.CREATED);

}
}
