package com.masai.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.exceptions.VaccineRegistrationException;
import com.masai.model.Member;
import com.masai.model.VaccineRegistration;
import com.masai.repository.MemberDao;
import com.masai.repository.VaccineRegistrationDao;

@Service
public class VaccineRegistrationServiceImpl implements VaccineRegistrationService {

	@Autowired
	private VaccineRegistrationDao vaccineRegistrationDao;

	@Autowired
	private MemberDao memberDao;

	public VaccineRegistrationServiceImpl(VaccineRegistrationDao vaccineRegistrationDao) {
		super();
		this.vaccineRegistrationDao = vaccineRegistrationDao;
	}

	@Override
	public List<VaccineRegistration> allVaccineRegistration() {
		List<VaccineRegistration> vaccineRegAll = vaccineRegistrationDao.findAll();

		if (vaccineRegAll.size() > 0)
			return vaccineRegAll;
		else
			throw new VaccineRegistrationException("No VaccineRegistration found...");
	}

	@Override
	public VaccineRegistration getVaccineRegistration(long mobileNo) {
		return vaccineRegistrationDao.findById(mobileNo).orElseThrow(() -> new VaccineRegistrationException(
				"VaccineRegistraion does not exist with this mobileNo :" + mobileNo));
	}

	@Override
	public Member getAllMember(long mobileNo) {
		Member members = memberDao.findById(mobileNo);
		return members;
	}

	@Override
	public VaccineRegistration addVaccineRegistration(VaccineRegistration reg) {

		Optional<VaccineRegistration> vr = vaccineRegistrationDao.findById(reg.getMobileno());

		if (vr.isPresent()) {
			throw new VaccineRegistrationException("Vaccination registration is present with the same MobileNo");
		}
		return vaccineRegistrationDao.save(reg);
	}

	@Override
	public VaccineRegistration updateVaccineRegistration(VaccineRegistration reg) {

		VaccineRegistration vc = vaccineRegistrationDao.findById(reg.getMobileno())
				.orElseThrow(() -> new VaccineRegistrationException("Registration not found"));
		vaccineRegistrationDao.save(reg);
		return reg;

	}

	@Override
	public boolean deleteVaccineRegistration(VaccineRegistration reg) {
		VaccineRegistration vr = vaccineRegistrationDao.findById(reg.getMobileno())
				.orElseThrow(() -> new VaccineRegistrationException("Vaccine Registration Not Found"));
		vaccineRegistrationDao.delete(vr);
		return true;

	}

}
