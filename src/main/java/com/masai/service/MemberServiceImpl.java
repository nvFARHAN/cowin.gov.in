package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor.OptimalPropertyAccessor;
import org.springframework.stereotype.Service;

import com.masai.exceptions.MemberNotFoundException;
import com.masai.model.AdharCard;
import com.masai.model.IdCard;
import com.masai.model.Member;
import com.masai.model.PanCard;
import com.masai.repository.AdharcardDao;
import com.masai.repository.IdCardDao;
import com.masai.repository.MemberDao;
import com.masai.repository.PancardDao;


@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao dao;
	
	@Autowired
	IdCardDao idDao;
	
	@Autowired
	AdharcardDao aDao;
	
	@Autowired
	PancardDao panDao;
	
	

	@Override
	public Member addMember(Member member) {
         
		Member m = dao.save(member);
		
		return m;
	}

	@Override
	public Member getMemberById(int idcardid) throws MemberNotFoundException{
		
		Optional<Member> mbyId=dao.findById(idcardid);
		if(mbyId.isPresent())
			return mbyId.get();
		else
			 throw new MemberNotFoundException("Member not found  with the idcard id:"+idcardid);		
	}

	@Override
	public Member getMemberByAdharNo(long adharno) throws MemberNotFoundException{
		AdharCard adharcard=aDao.findAdharcardByadharNo(adharno);
		if(adharcard==null)
			throw new MemberNotFoundException("Member not found  with the panNo:"+adharno);
		else {
		IdCard idcard=idDao.findByAdharcard(adharcard);
		if(idcard==null)
			throw new MemberNotFoundException("Member not found  with the adharNo id:"+adharno);
		else {
			Optional<Member> mbyId=dao.findById(idcard.getId());
			if(mbyId.isPresent())
				return mbyId.get();
			else
				 throw new MemberNotFoundException("Member not found  ");
		}
		}	
		
	}

	

	@Override
	public Member getMemberByPanNo(String panNo) throws MemberNotFoundException {
		Optional<PanCard> pancard=panDao.findById(panNo);
		if(pancard==null)
			throw new MemberNotFoundException("Member not found  with the panNo:"+panNo);
		else {
			IdCard idcard=idDao.findByPancard(pancard);
			if(idcard==null)
				throw new MemberNotFoundException("Member not found idcard with the  panNo:"+panNo);
			else
			{
			Optional<Member> mbyId=dao.findById(idcard.getId());
			if(mbyId.isPresent())
				return mbyId.get();
			else
				 throw new MemberNotFoundException("Member not found  with ");
		}
		}
		
		
	}

	@Override
	public Member updateMember(Member member) throws MemberNotFoundException{
		Optional<Member> mId=dao.findById(member.getMemberId());
		if(mId.isPresent())
			return dao.save(member);
		else
			 throw new MemberNotFoundException("Member not found with the member id :"+member.getMemberId());		
	}
	

	@Override
	public boolean deleteMember(Member member) throws MemberNotFoundException{
		Optional<Member> mId=dao.findById(member.getMemberId());
		if(mId.isPresent())
		{ dao.delete(member);
		 return true;
		}
		else
			 throw new MemberNotFoundException("Member not found with the member id :"+member.getMemberId());
	}

}
