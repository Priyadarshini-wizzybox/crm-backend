package com.wizzybox.crmbackend.repository;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.wizzybox.crmbackend.model.Signup;




@Repository
public interface SignupRepository  extends JpaRepository<Signup,Integer> {



	
}
