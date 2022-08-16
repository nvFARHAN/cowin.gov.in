package com.masai.service;

import java.util.List;

import com.masai.model.Member;
import com.masai.model.VaccineRegistration;

public interface VaccineRegistrationService {

	public List<VaccineRegistration> allVaccineRegistration();

	public VaccineRegistration getVaccineRegistration(String mobileNo);

	public List<Member> getAllMember(String mobileNo);

	public VaccineRegistration addVaccineRegistration(String mobNo);

	public VaccineRegistration updateVaccineRegistration(String mobNo, String newMobNo);

	public boolean deleteVaccineRegistration(String mobNo);

}
