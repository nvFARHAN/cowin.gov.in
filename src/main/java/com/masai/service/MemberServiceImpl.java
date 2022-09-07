package com.masai.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.MemberNotFoundException;
import com.masai.model.AdharCard;
import com.masai.model.Appointment;
import com.masai.model.CurrentAdminSession;
import com.masai.model.CurrentUserSession;
import com.masai.model.IdCard;
import com.masai.model.Member;
import com.masai.model.PanCard;
import com.masai.model.Vaccine;
import com.masai.model.VaccineCount;
import com.masai.model.VaccineRegistration;
import com.masai.repository.AdminSessionDAO;
import com.masai.repository.AppointmentDao;
import com.masai.repository.IdCardDao;
import com.masai.repository.MemberDao;
import com.masai.repository.UserSessionDAO;
import com.masai.repository.VaccineCountDao;
import com.masai.repository.VaccineDao;
import com.masai.repository.VaccineRegistrationDao;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao dao;

	@Autowired
	IdCardDao idDao;

	@Autowired
	VaccineRegistrationDao vrDao;

	@Autowired
	AppointmentDao apDao;

	@Autowired
	VaccineDao vDao;

	@Autowired
	VaccineCountDao countDao;
	
	@Autowired
	private AdminSessionDAO adminSessionDAO;
	
	@Autowired
	private UserSessionDAO userSessionDAO;

	@Override
	public Member addMemberbyMobileNo(Member member, String mobileNo,String key) throws MemberNotFoundException {
		
		 Optional<CurrentUserSession> optCurrUser= userSessionDAO.findByUuid(key);
			
			if(!optCurrUser.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
	
		
		
		Optional<VaccineRegistration> vacc = vrDao.findById(mobileNo);
		if (vacc.isPresent()) {
			IdCard idcard = idDao.findByAdharcard(member.getIdCard().getAdharcard());
			if (idcard == null)

			{
				member.setVaccineRegistration(vacc.get());
				member.setDose1Date(null);
				member.setDose2Date(null);
				member.setDose1Status(false);
				member.setDose2Status(false);
				return dao.save(member);
			} else
				throw new MemberNotFoundException("Member is already present");
		} else
			throw new MemberNotFoundException("This MOBILE NUMBER is NOT REGISTERED:" + mobileNo);
	}

	@Override
	public Member updateMember(Member member, Integer mid,String key) throws MemberNotFoundException {
	
		 Optional<CurrentAdminSession> optCurrAdmin= adminSessionDAO.findByUuid(key);
		 Optional<CurrentUserSession> optCurrUser= userSessionDAO.findByUuid(key);
			
			if(!optCurrAdmin.isPresent()&&!optCurrUser.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
			
		
		Optional<Member> mId = dao.findById(mid);
		if (mId.isPresent()) {
			Member exist = mId.get();
			if (member.getIdCard() != null) {
				IdCard id = exist.getIdCard();
				if (member.getIdCard().getDob() != null)
					id.setDob(member.getIdCard().getDob());
				if (member.getIdCard().getCity() != null)
					id.setCity(member.getIdCard().getCity());
				if (member.getIdCard().getGender() != null)
					id.setGender(member.getIdCard().getGender());
				if (member.getIdCard().getAddress() != null)
					id.setAddress(member.getIdCard().getAddress());
				if (member.getIdCard().getPincode() != null)
					id.setPincode(member.getIdCard().getPincode());
				if (member.getIdCard().getState() != null)
					id.setState(member.getIdCard().getState());

				if (member.getIdCard().getAdharcard() != null) {
					AdharCard adar = exist.getIdCard().getAdharcard();
					adar.setAdharNo(member.getIdCard().getAdharcard().getAdharNo());
				}

				if (member.getIdCard().getPancard() != null) {
					PanCard pan = exist.getIdCard().getPancard();
					pan.setPanNo(member.getIdCard().getPancard().getPanNo());
				}
			}
			return dao.save(exist);
		} else
			throw new MemberNotFoundException("Member not found with the MEMBER ID :" + member.getMemberId());
	}

	@Override
	public Member getMemberById(Integer idcardid,String key) throws MemberNotFoundException {
		
		 Optional<CurrentAdminSession> optCurrAdmin= adminSessionDAO.findByUuid(key);
		 Optional<CurrentUserSession> optCurrUser= userSessionDAO.findByUuid(key);
			
			if(!optCurrAdmin.isPresent()&&!optCurrUser.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
			
		
		Optional<IdCard> idcard = idDao.findById(idcardid);
		if (idcard != null) {
			Member mbyId = dao.findByIdCard(idcard);
			if (mbyId != null) {
				List<Appointment> appointment = apDao.findByMember(mbyId);
				mbyId.setAppointments(appointment);
				return mbyId;
			} else
				throw new MemberNotFoundException("Member not found  with the IDCARD ID:" + idcardid);
		} else
			throw new MemberNotFoundException("Member not found  with the IDCARD ID:" + idcardid);
	}

	@Override
	public boolean deleteMember(Integer mid,String key) throws MemberNotFoundException {
		
		 Optional<CurrentUserSession> optCurrUser= userSessionDAO.findByUuid(key);
			
			if(!optCurrUser.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
	
		
		Optional<Member> mId = dao.findById(mid);
		if (mId.isPresent()) {
			Member exist = mId.get();
			if (exist.getVaccineRegistration() != null)
				vrDao.delete(exist.getVaccineRegistration());
			if (exist.getIdCard() != null)
				idDao.delete(exist.getIdCard());
			if (exist.getAppointments() != null)
				apDao.deleteAll(exist.getAppointments());
			dao.delete(exist);
			return true;
		} else
			throw new MemberNotFoundException("Member not found with the MEMBER ID :" + mid);
	}

	@Override
	public Member getMemberByPanNo(String panNo,String key) throws MemberNotFoundException {
	
		 Optional<CurrentAdminSession> optCurrAdmin= adminSessionDAO.findByUuid(key);
			
			if(!optCurrAdmin.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
		
		IdCard idcard = idDao.findByPancard(new PanCard(panNo));
		if (idcard != null) {
			Optional<IdCard> id = idDao.findById(idcard.getId());
			Member mbyId = dao.findByIdCard(id);
			if (mbyId != null)
				return mbyId;
			else
				throw new MemberNotFoundException("Member doesnot exist with the PAN NUMBER : " + panNo);
		} else
			throw new MemberNotFoundException("Member doesnot exist with the PAN NUMBER : " + panNo);
	}

	@Override
	public boolean deleteMemberRecord(Member member,String key) throws MemberNotFoundException {
		
		 Optional<CurrentAdminSession> optCurrAdmin= adminSessionDAO.findByUuid(key);
			
			if(!optCurrAdmin.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
		
		
		Optional<Member> mId = dao.findById(member.getMemberId());
		if (mId.isPresent()) {
			Member exist = mId.get();
			if (exist.getVaccineRegistration() != null)
				vrDao.delete(exist.getVaccineRegistration());
			if (exist.getIdCard() != null)
				idDao.delete(exist.getIdCard());
			if (exist.getAppointments() != null)
				apDao.deleteAll(exist.getAppointments());
			if (exist.getVaccine() != null)
				vDao.delete(exist.getVaccine());
			dao.delete(member);
			return true;
		} else
			throw new MemberNotFoundException("Member not found with the MEMBER ID :" + member.getMemberId());
	}

	@Override
	public Member getMemberByAdharNo(Long adharno,String key) throws MemberNotFoundException {
		 Optional<CurrentAdminSession> optCurrAdmin= adminSessionDAO.findByUuid(key);
			
			if(!optCurrAdmin.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
		
		IdCard idcard = idDao.findByAdharcard(new AdharCard(adharno));
		Optional<IdCard> idcard1 = idDao.findById(idcard.getId());
		if (idcard1 != null) {
			Member mbyId = dao.findByIdCard(idcard1);
			if (mbyId != null)
				return mbyId;
			else
				throw new MemberNotFoundException("Member doesnot exist with the ADHAR NUMBER : " + adharno);
		} else
			throw new MemberNotFoundException("Member doesnot exist with the ADHAR NUMBER : " + adharno);

	}

	@Override
	public Member updatedoseStatus(Member member, Integer mid,String key) throws MemberNotFoundException {
		
		 Optional<CurrentAdminSession> optCurrAdmin= adminSessionDAO.findByUuid(key);
			
			if(!optCurrAdmin.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
			
		Optional<Member> mId = dao.findById(mid);
		if (mId.isPresent()) {
			Member exist = mId.get();
			LocalDate present = LocalDate.now();
			LocalDate pastDate = present.minusDays(3);

			if (member.getDose1Date() != null) {
				LocalDate given1 = member.getDose1Date();
				if (member.getDose1Date() != null && (given1.isBefore(present) || present.isEqual(given1))
						&& given1.isAfter(pastDate)) {
					exist.setDose1Date(member.getDose1Date());
					if (exist.getDose1Date() != null) {
						exist.setDose1Status(true);
						Vaccine vaccine = vDao.findByvaccineName(member.getVaccine().getVaccineName());
						exist.setVaccine(vaccine);
						VaccineCount vc = countDao.findByvaccine(vaccine);
						vc.setQuantity(vc.getQuantity() - 1);
					}
				}

				else if (member.getDose1Date() != null && present.isBefore(given1))
					throw new MemberNotFoundException("Future date is given in DOSE 1 DATE area");

				else if (member.getDose1Date() != null && pastDate.isAfter(given1))
					throw new MemberNotFoundException(" date is 3 days before the present date(DOSE 1)");
			}
			if (exist.getDose1Date() == null && member.getDose2Date() != null)
				throw new MemberNotFoundException(" Dose 1 not taken");

			if (member.getDose2Date() != null) {
				LocalDate given2 = member.getDose2Date();
				if (given2.isBefore(present)
						|| present.isEqual(given2) && given2.isAfter(pastDate) && exist.getDose1Date() != null) {
					System.out.println("call");
					exist.setDose2Date(member.getDose2Date());
					if (exist.getDose2Date() != null) {
						exist.setDose2Status(true);
						Vaccine vaccine = exist.getVaccine();
						VaccineCount vc = countDao.findByvaccine(vaccine);
						vc.setQuantity(vc.getQuantity() - 1);
					}

				}

				else if (member.getDose2Date() != null && present.isBefore(given2))
					throw new MemberNotFoundException("Future date is given in DOSE 2 DATE area");
				else if (member.getDose1Date() != null && pastDate.isAfter(given2))
					throw new MemberNotFoundException("date is 3 days before the present date(DOSE 2)");

			}

			if (exist.getDose2Date() != null)
				exist.setDose2Status(true);
			return dao.save(exist);
		} else
			throw new MemberNotFoundException("Member not found with the MEMBER ID :" + member.getMemberId());

	}

//	@Override
//	public Member addMember(Member member) {
//		if(member.getIdCard()!=null)
//		{
//			if(member.getIdCard().getId()!=null)
//			{
//				Optional<IdCard> idcard=idDao.findById(member.getIdCard().getId());
//		    if(idcard.isPresent())
//		    {
//		    	Member mbyId = dao.findByIdCard(idcard);
//		    	if(mbyId==null)
//		    	member.setIdCard(idcard.get());
//		    	else
//		    		throw new MemberNotFoundException("Member already exist with the Idcard id :"+member.getIdCard().getId());
//		    }
//		    else
//	    		throw new MemberNotFoundException("Invalid Idcard id ");
//				}
//		}
//
//	    if(member.getAppointments()!=null)
//	    	{
//	    	List<Appointment> app=member.getAppointments();
//	    	for(Appointment appointment:app)
//	    	{
//	    		appointment.setMember(member);
//	    	}
//	    	}
//	  
//	    if(member.getVaccineRegistration()!=null)
//	    {   if(member.getVaccineRegistration().getMobileno()!=0)
//	    	{VaccineRegistration vacc=vrDao.findBymobileno(member.getVaccineRegistration().getMobileno());
//	    	if(vacc!=null)
//	    	{
//	    		member.setVaccineRegistration(vacc);
//	    	}
//	    }}
//	    
//	    
//	    
//		 LocalDate present=LocalDate.now();
//		 LocalDate pastDate=present.minusMonths(6);
//		 System.out.println(pastDate);
//		 if(member.getDose1Date()!=null)
//		 {
//			 LocalDate  given1=member.getDose1Date(); 	
//			 if(member.getDose1Date()!=null&&present.isBefore(given1))
//				throw new MemberNotFoundException("Future date is given in DOSE 1 DATE area" ); 
//			 else if(member.getDose1Date()!=null&&pastDate.isAfter(given1))
//					throw new MemberNotFoundException(" date is before 6 months (DOSE 1)" ); 	
//			 else if(member.getDose1Date()!=null&&(given1.isBefore(present)||present.isEqual(given1))&&given1.isAfter(pastDate))
//				 member.setDose1Status(true);
//	
//		 if(member.getDose2Date()!=null)
//		 {
//			 LocalDate  given2=member.getDose2Date();
//			 if(member.getDose2Date()!=null&&present.isBefore(given2))
//					throw new MemberNotFoundException("Future date is given in DOSE 2 DATE area" );	 
//			 else if(member.getDose1Date()!=null&&pastDate.isAfter(given2))
//					throw new MemberNotFoundException(" date is before 6 months (DOSE 1)" ); 
//		
//		 }
//		 }
//		 if(member.getDose1Date()==null&&member.getDose2Date()!=null)
//				throw new MemberNotFoundException(" Dose 1 status is false" ); 
//		
//		 if(member.isDose1Status()==true&&member.getDose1Date()==null)
//			 throw new MemberNotFoundException(" Dose 1 DATE Not Entered" ); 
//		 if(member.isDose2Status()==true&&member.getDose2Date()==null)
//			 throw new MemberNotFoundException(" Dose 2 DATE Not Entered" );
//		 
//			return dao.save(member);	
//	}

//	@Override
//	public Member updateMember(Member member) throws MemberNotFoundException {
//		Optional<Member> mId = dao.findById(member.getMemberId());
//		if (mId.isPresent())
//		{
//			Member exist=mId.get();
//				if(member.getIdCard()!=null)
//				{
//					IdCard id=exist.getIdCard();
//					if(member.getIdCard().getDob()!=null)
//						id.setDob(member.getIdCard().getDob());
//				  if(member.getIdCard().getCity()!=null)
//					    id.setCity(member.getIdCard().getCity());
//				  if(member.getIdCard().getGender()!=null)
//					  id.setGender(member.getIdCard().getGender());
//				  if(member.getIdCard().getAddress()!=null)
//					  id.setAddress(member.getIdCard().getAddress());
//				  if(member.getIdCard().getPincode()!=null)
//					  id.setPincode(member.getIdCard().getPincode());
//				  if(member.getIdCard().getState()!=null)
//					  id.setState(member.getIdCard().getState());
//				  
//				  if(member.getIdCard().getAdharcard()!=null)
//				  {
//					  AdharCard adar=exist.getIdCard().getAdharcard();
//					  adar.setAdharNo(member.getIdCard().getAdharcard().getAdharNo());
//				  }
//					
//				  if(member.getIdCard().getPancard()!=null)
//				  {
//					  PanCard pan=exist.getIdCard().getPancard();
//					  pan.setPanNo(member.getIdCard().getPancard().getPanNo());
//				  }	 
//				}	
//			return dao.save(exist);}
//		else
//			throw new MemberNotFoundException("Member not found with the MEMBER ID :" + member.getMemberId());
//	}

//	@Override
//	public Member updatedoseStatus(Member member) throws MemberNotFoundException {
//		Optional<Member> mId = dao.findById(member.getMemberId());
//		if (mId.isPresent())
//		{
//			Member exist=mId.get();
////			if(exist.isDose1Status()==true)
////				{exist.setDose1Status(member.isDose1Status());
////				exist.setDose1Date(null);
////				};
//			
//			 LocalDate present=LocalDate.now();
//			 LocalDate pastDate=present.minusMonths(6);
//			 
//			 
//			 if(member.getDose1Date()!=null)
//			 {
//				 LocalDate  given1=member.getDose1Date(); 
//				 if(member.getDose1Date()!=null&&(given1.isBefore(present)||present.isEqual(given1))&&given1.isAfter(pastDate))
//					 exist.setDose1Date(member.getDose1Date());
//					
//				else if(member.getDose1Date()!=null&&present.isBefore(given1))
//					throw new MemberNotFoundException("Future date is given in DOSE 1 DATE area" ); 
//				 
//				else if(member.getDose1Date()!=null&&pastDate.isAfter(given1))
//					throw new MemberNotFoundException(" date is before 6 months (DOSE 1)" ); 
//		
//			 if(member.getDose2Date()!=null)
//			 {
//				 LocalDate  given2=member.getDose2Date();
//				if(member.getDose2Date()!=null&&given2.isBefore(present)||present.isEqual(given2)&&given2.isAfter(pastDate))
//					 exist.setDose2Date(member.getDose2Date());
//						
//				else if(member.getDose2Date()!=null&&present.isBefore(given2))
//						throw new MemberNotFoundException("Future date is given in DOSE 2 DATE area" );	
//				else if(member.getDose1Date()!=null&&pastDate.isAfter(given2))
//					throw new MemberNotFoundException(" date is before 6 months (DOSE 1)" ); 
//				 
//			 }
//			 
//			 }
//			 if(member.getDose1Date()==null&&member.getDose2Date()!=null)
//					throw new MemberNotFoundException(" Dose 1 status is false" );
//			    if(exist.getDose1Date()!=null)
//			    	exist.setDose1Status(true);
//			    if(exist.getDose2Date()!=null)
//			    	exist.setDose2Status(true);
//			    return dao.save(exist);
//		}
//		else
//			throw new MemberNotFoundException("Member not found with the MEMBER ID :" + member.getMemberId());
//		
//	
//	}
}