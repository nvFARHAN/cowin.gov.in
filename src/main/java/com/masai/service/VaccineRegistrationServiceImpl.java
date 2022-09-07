package com.masai.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.VaccineRegistrationException;
import com.masai.model.CurrentAdminSession;
import com.masai.model.CurrentUserSession;
import com.masai.model.Member;
import com.masai.model.VaccineRegistration;
import com.masai.repository.UserSessionDAO;
import com.masai.repository.VaccineRegistrationDao;

@Service
public class VaccineRegistrationServiceImpl implements VaccineRegistrationService {

	@Autowired
	private VaccineRegistrationDao vaccineRegistrationDao;

	@Autowired
	private VaccineRegistrationDao vrdao;
	
	@Autowired
	private UserSessionDAO userSessionDAO;

	@Override
	public List<VaccineRegistration> allVaccineRegistration() {
		List<VaccineRegistration> vaccineRegAll = vaccineRegistrationDao.findAll();

		if (vaccineRegAll.size() > 0)
			return vaccineRegAll;
		else
			throw new VaccineRegistrationException("No VaccineRegistration found...");
	}

	@Override
	public VaccineRegistration getVaccineRegistration(String mobileNo,String key) {
		
		 Optional<CurrentUserSession> optCurrUser= userSessionDAO.findByUuid(key);
			
			if(!optCurrUser.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
	
		
		return vaccineRegistrationDao.findById(mobileNo).orElseThrow(() -> new VaccineRegistrationException(
				"VaccineRegistraion does not exist with this mobileNo :" + mobileNo));
	}

	@Override
	public List<Member> getAllMember(String mobileNo,String key) {
		
		 Optional<CurrentUserSession> optCurrUser= userSessionDAO.findByUuid(key);
			
			if(!optCurrUser.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
	
		
		Optional<VaccineRegistration> vr = vrdao.findById(mobileNo);
		if (vr.isPresent()) {
			VaccineRegistration reg = vr.get();
			return reg.getMembers();
		} else
			throw new VaccineRegistrationException("Registration Not Found....");
	}

	@Override
	public VaccineRegistration addVaccineRegistration(String mobNo,String key) {

		 Optional<CurrentUserSession> optCurrUser= userSessionDAO.findByUuid(key);
			
			if(!optCurrUser.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
			
		Optional<VaccineRegistration> vr = vaccineRegistrationDao.findById(mobNo);

		if (vr.isPresent()) {
			throw new VaccineRegistrationException("Vaccination registration is present with the same MobileNo");
		}
		return vaccineRegistrationDao.save(new VaccineRegistration(mobNo, LocalDate.now(), null));
	}

	@Override
	public VaccineRegistration updateVaccineRegistration(String mobNo, String newMobNo,String key) {

		 Optional<CurrentUserSession> optCurrUser= userSessionDAO.findByUuid(key);
			
			if(!optCurrUser.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
	
		
		VaccineRegistration vc = vaccineRegistrationDao.findById(mobNo)
				.orElseThrow(() -> new VaccineRegistrationException("Registration not found"));
		vc.setMobileno(newMobNo);
		return vaccineRegistrationDao.save(vc);

	}

	@Override
	public boolean deleteVaccineRegistration(String mobNo,String key) {
		
		 Optional<CurrentUserSession> optCurrUser= userSessionDAO.findByUuid(key);
			
			if(!optCurrUser.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
	
		
		VaccineRegistration vr = vaccineRegistrationDao.findById(mobNo)
				.orElseThrow(() -> new VaccineRegistrationException("Vaccine Registration Not Found"));
		vaccineRegistrationDao.delete(vr);
		return true;

	}

}