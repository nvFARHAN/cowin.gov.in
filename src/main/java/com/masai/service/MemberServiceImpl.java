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

			return dao.save(member);
		
	}

	@Override
	public Member getMemberById(int idcardid) throws MemberNotFoundException {

		Optional<Member> mbyId = dao.findById(idcardid);
		if (mbyId.isPresent())
			return mbyId.get();
		else
			throw new MemberNotFoundException("Member not found  with the idcard id:" + idcardid);
	}



	@Override
	public Member updateMember(Member member) throws MemberNotFoundException {
		Optional<Member> mId = dao.findById(member.getMemberId());
		if (mId.isPresent())
			return dao.save(member);
		else
			throw new MemberNotFoundException("Member not found with the member id :" + member.getMemberId());
	}

	@Override
	public boolean deleteMember(Member member) throws MemberNotFoundException {
		Optional<Member> mId = dao.findById(member.getMemberId());
		if (mId.isPresent()) {
			dao.delete(member);
			return true;
		} else
			throw new MemberNotFoundException("Member not found with the member id :" + member.getMemberId());
	}

	@Override
	public Member getMemberByAdharNo(long adharno) throws MemberNotFoundException {
		IdCard idcard = idDao.findByAdharcard(new AdharCard(adharno));
		if (idcard == null)
			throw new MemberNotFoundException("Member not found  with the adharNo id:" + adharno);
		else {
			Optional<Member> mbyId = dao.findById(idcard.getId());
			if (mbyId.isPresent())
				return mbyId.get();
			else
				throw new MemberNotFoundException("Member not found  ");
		}
	}

	@Override
	public Member getMemberByPanNo(String panNo) throws MemberNotFoundException {
		IdCard idcard = idDao.findByPancard(new PanCard(panNo));
		
			if (idcard == null)
				throw new MemberNotFoundException("Member not found idcard with the  panNo:" + panNo);
			else {
				Optional<Member> mbyId = dao.findById(idcard.getId());
				if (mbyId.isPresent())
					return mbyId.get();
				else
					throw new MemberNotFoundException("Member not found  with ");
			}
		}
	

}
