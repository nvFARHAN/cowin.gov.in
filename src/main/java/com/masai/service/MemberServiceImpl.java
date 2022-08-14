package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.MemberNotFoundException;
import com.masai.model.AdharCard;
import com.masai.model.Appointment;
import com.masai.model.IdCard;
import com.masai.model.Member;
import com.masai.model.PanCard;
import com.masai.model.VaccineRegistration;
import com.masai.repository.AppointmentDao;
import com.masai.repository.IdCardDao;
import com.masai.repository.MemberDao;
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

	@Override
	public Member addMember(Member member) {
		if(member.getIdCard()!=null)
		{if(member.getIdCard().getId()!=null)
			{Optional<IdCard> idcard=idDao.findById(member.getIdCard().getId());
		member.setIdCard(idcard.get());}
		}

	    if(member.getAppointments()!=null)
	    	{
	    	List<Appointment> app=member.getAppointments();
	    	for(Appointment appointment:app)
	    	{
	    		appointment.setMember(member);
	    	}
	    	}
	    ;
		
			return dao.save(member);
		
		
	}

	@Override
	public Member getMemberById(int idcardid) throws MemberNotFoundException {
		Optional<IdCard> idcard=idDao.findById(idcardid);
		if(idcard!=null)
		{Member mbyId = dao.findByIdCard(idcard);
		if (mbyId!=null)
		{ List<Appointment> appointment=apDao.findBymember(mbyId);
			mbyId.setAppointments(appointment);
			return mbyId;}
		else
			throw new MemberNotFoundException("Member not found  with the idcard id:" + idcardid);
		}
		else
			throw new MemberNotFoundException("Member not found  with the idcard id:" + idcardid);
	}



	@Override
	public Member updateMember(Member member) throws MemberNotFoundException {
		Optional<Member> mId = dao.findById(member.getMemberId());
		if (mId.isPresent())
		{
			Member exist=mId.get();
			
			if(member.getDose1Date()!=null)
				exist.setDose1Date(member.getDose1Date());
			if(member.getDose2Date()!=null)
				exist.setDose2Date(member.getDose2Date());	
			    if(exist.getDose1Date()!=null)
			    	exist.setDose1Status(true);
			    if(exist.getDose2Date()!=null)
			    	exist.setDose2Status(true);
			   
			   
	
				if(member.getIdCard()!=null)
				{
					IdCard id=exist.getIdCard();
					if(member.getIdCard().getDob()!=null)
						id.setDob(member.getIdCard().getDob());
				  if(member.getIdCard().getCity()!=null)
					    id.setCity(member.getIdCard().getCity());
				  if(member.getIdCard().getGender()!=null)
					  id.setGender(member.getIdCard().getGender());
				  if(member.getIdCard().getAddress()!=null)
					  id.setAddress(member.getIdCard().getAddress());
				  if(member.getIdCard().getPincode()!=null)
					  id.setPincode(member.getIdCard().getPincode());
				  if(member.getIdCard().getState()!=null)
					  id.setState(member.getIdCard().getState());
				  
				  if(member.getIdCard().getAdharcard()!=null)
				  {
					  AdharCard adar=exist.getIdCard().getAdharcard();
					  adar.setAdharNo(member.getIdCard().getAdharcard().getAdharNo());
				  }
					
				  if(member.getIdCard().getPancard()!=null)
				  {
					  PanCard pan=exist.getIdCard().getPancard();
					  pan.setPanNo(member.getIdCard().getPancard().getPanNo());
				  }
					 
				}
			  	  
				
				
				
			return dao.save(exist);}
		else
			throw new MemberNotFoundException("Member not found with the member id :" + member.getMemberId());
	}

	@Override
	public boolean deleteMember(Member member) throws MemberNotFoundException {
		Optional<Member> mId = dao.findById(member.getMemberId());
		if (mId.isPresent()) {
			Member exist=mId.get();
//			Optional<IdCard> id=idDao.findById(exist.getIdCard().getId());
//			IdCard exid=id.get();
			vrDao.delete(exist.getVaccineRegistration());
			idDao.delete(exist.getIdCard());
			apDao.deleteAll(exist.getAppointments());
			vDao.delete(exist.getVaccine());
			dao.delete(member);
			return true;
		} else
			throw new MemberNotFoundException("Member not found with the member id :" + member.getMemberId());
	}

	@Override
	public Member getMemberByAdharNo(long adharno) throws MemberNotFoundException {
		IdCard idcard = idDao.findByAdharcard(new AdharCard(adharno));
		Optional<IdCard> idcard1=idDao.findById(idcard.getId());
		if(idcard1!=null)
		{Member mbyId = dao.findByIdCard(idcard1);
			if (mbyId!=null)
				return mbyId;
			else
				throw new MemberNotFoundException("Member doesnot exist with the adharNo : "+adharno);
			}
			else
				throw new MemberNotFoundException("Member doesnot exist with the adharNo : "+adharno);

	}

	@Override
	public Member getMemberByPanNo(String panNo) throws MemberNotFoundException {
		IdCard idcard = idDao.findByPancard(new PanCard(panNo));
		if(idcard!=null)
		{Optional<IdCard> id=idDao.findById(idcard.getId());
				Member mbyId = dao.findByIdCard(id);
				if (mbyId!=null)
					return mbyId;
				else
					throw new MemberNotFoundException("Member doesnot exist with the adharNo : "+panNo);
			}
	else
		throw new MemberNotFoundException("Member doesnot exist with the adharNo : "+panNo);
	

}
}
