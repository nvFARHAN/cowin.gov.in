package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor.OptimalPropertyAccessor;
import org.springframework.stereotype.Service;

import com.masai.exceptions.MemberNotFoundException;
import com.masai.model.IdCard;
import com.masai.model.Member;
import com.masai.repository.MemberDao;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao dao;

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
		
//		Optional<IdCard> mbyId=dao.findByAdharNo(adharno);
//		if(mbyId.isPresent())
//			return mbyId.get();
//		else
//			 throw new MemberNotFoundException("Member not found");		
		return null;
	}

	

	@Override
	public Member getMemberByPanNo(String panNo) throws MemberNotFoundException {
		// TODO Auto-generated method stub
		return null;
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
