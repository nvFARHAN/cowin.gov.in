package com.masai.service;

import java.util.List;

import com.masai.model.Member;
import com.masai.model.VaccineRegistration;

public interface VaccineRegistrationService {

	public List<VaccineRegistration> allVaccineRegistration();
	
	public VaccineRegistration getVaccineRegistration(long mobileNo);
	
	public Member getAllMember(long mobileNo);
	
	public VaccineRegistration addVaccineRegistration(VaccineRegistration reg);

	public VaccineRegistration updateVaccineRegistration(VaccineRegistration reg);

	public boolean deleteVaccineRegistration(VaccineRegistration reg);

	

}
