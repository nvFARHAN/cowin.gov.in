package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.MemberNotFoundException;
import com.masai.model.AdharCard;
import com.masai.model.IdCard;
import com.masai.model.PanCard;
import com.masai.repository.IdCardDao;

@Service
public class IdCardServiceImpl implements IdCardService {

	@Autowired
	private IdCardDao idDao;

	@Override
	public IdCard getIdcardByPanNo(String panNo) throws MemberNotFoundException {

		IdCard idcard = idDao.findByPancard(new PanCard(panNo));
		if (idcard == null)
			throw new MemberNotFoundException("Member not found idcard with the  panNo:" + panNo);
		else
			return idcard;
	}

	@Override
	public IdCard getIdCardByAdharNo(Long adharno) throws MemberNotFoundException {

		IdCard idcard = idDao.findByAdharcard(new AdharCard(adharno));
		if (idcard == null)
			throw new MemberNotFoundException("Member not found  with the adharNo id:" + adharno);
		else
			return idcard;
	}

	@Override
	public IdCard addIdCard(IdCard idCard) {
//		Optional<IdCard> opt = idDao.findById(idCard.getId());

		return idDao.save(idCard);
	}

}