package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.VaccineRegistrationException;
import com.masai.exceptions.VaccineRepositoryException;
import com.masai.model.Member;
import com.masai.model.VaccineRegistration;
import com.masai.repository.VaccineRegistrationDao;

@Service
public class VaccineRegistrationServiceImpl implements VaccineRegistrationService {

	@Autowired
	private VaccineRegistrationDao vaccineRegistrationDao;

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
	public List<Member> getAllMember(long mobileNo) {
		List<Member> members
	}

	@Override
	public VaccineRegistration addVaccineRegistration(VaccineRegistration reg) {
		return vaccineRegistrationDao.save(reg);
	}

	@Override
	public VaccineRegistration updateVaccineRegistration(VaccineRegistration reg) {
		Optional<VaccineRegistration> opt = vaccineRegistrationDao.findById(null);

		if (opt.isPresent()) {

			VaccineRegistration existingVaccine = opt.get();

			return vaccineRegistrationDao.save(reg);

		} else
			throw new VaccineRepositoryException("Invalid VaccineRegistration Details..");

	}

	@Override
	public VaccineRegistration deleteVaccineRegistration(VaccineRegistration reg) {
		VaccineRegistration existingVaccineReg = vaccineRegistrationDao.findById(reg)
				.orElseThrow(() -> new VaccineRegistrationException(
						"VaccineRegistration does not exist with this vaccineRegistration :" + reg));

		vaccineRegistrationDao.delete(existingVaccineReg);

		return existingVaccineReg;
	}

}
