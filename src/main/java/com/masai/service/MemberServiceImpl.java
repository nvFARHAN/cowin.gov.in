package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.MemberNotFoundException;
import com.masai.model.AdharCard;
import com.masai.model.IdCard;
import com.masai.model.Member;
import com.masai.model.PanCard;
import com.masai.repository.IdCardDao;
import com.masai.repository.MemberDao;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao dao;

	@Autowired
	IdCardDao idDao;

	@Override
	public Member addMember(Member member) {
		if(member.getIdCard()!=null)
		{if(member.getIdCard().getId()!=null)
			{Optional<IdCard> idcard=idDao.findById(member.getIdCard().getId());
		member.setIdCard(idcard.get());}
		}
		
			return dao.save(member);
		
		
	}

	@Override
	public Member getMemberById(int idcardid) throws MemberNotFoundException {
		Optional<IdCard> idcard=idDao.findById(idcardid);
		Member mbyId = dao.findByIdCard(idcard);
		if (mbyId!=null)
			return mbyId;
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
			    else
			    	exist.setDose1Status(false);
			    if(exist.getDose2Date()!=null)
			    	exist.setDose2Status(true);
			    else
			    	exist.setDose2Status(false);
				if(member.getIdCard()!=null)
				{
					Optional<IdCard> idcard1=idDao.findById(member.getIdCard().getId());
					IdCard id=idcard1.get();
					if(member.getIdCard().getDob()!=null)
						id.setDob(member.getIdCard().getDob());
				  if(member.getIdCard().getCity()!=null)
					    id.setCity(member.getIdCard().getCity());
				  if(member.getIdCard().getGender()!=null)
					  id.setGender(member.getIdCard().getGender());
				  if(member.getIdCard().getAddress()!=null)
					  id.setAddress(member.getIdCard().getAddress());
				  if(member.getIdCard().getAdharcard()!=null)
					  id.setAdharcard(member.getIdCard().getAdharcard());
				  if(member.getIdCard().getPancard()!=null)
//					  if(member.getIdCard().getPancard().getPanNo()!=null)
//					  id.setPancard(member.getIdCard().getPancard().getPanNo());
//				  if(member.getIdCard().getPincode()!=null)
//					  id.setPincode(member.getIdCard().getPincode());
				  if(member.getIdCard().getState()!=null)
					  id.setState(member.getIdCard().getState());
						  ;
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
			idDao.delete(exist.getIdCard());
			dao.delete(member);
			return true;
		} else
			throw new MemberNotFoundException("Member not found with the member id :" + member.getMemberId());
	}

	@Override
	public Member getMemberByAdharNo(long adharno) throws MemberNotFoundException {
		IdCard idcard = idDao.findByAdharcard(new AdharCard(adharno));
		Optional<IdCard> idcard1=idDao.findById(idcard.getId());
		Member mbyId = dao.findByIdCard(idcard1);
			if (mbyId!=null)
				return mbyId;
			else
				throw new MemberNotFoundException("Member not found  ");

	}

	@Override
	public Member getMemberByPanNo(String panNo) throws MemberNotFoundException {
		IdCard idcard = idDao.findByPancard(new PanCard(panNo));
		Optional<IdCard> id=idDao.findById(idcard.getId());
				Member mbyId = dao.findByIdCard(id);
				if (mbyId!=null)
					return mbyId;
				else
					throw new MemberNotFoundException("Member not found  with ");
			}

	

}
