package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.IdCardException;
import com.masai.exceptions.IdCardNotFoundException;
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
			throw new IdCardNotFoundException("Idcard not found with the  panNo:" + panNo);
		else
			return idcard;
	}

	@Override
	public IdCard getIdCardByAdharNo(Long adharno) throws MemberNotFoundException {

		IdCard idcard = idDao.findByAdharcard(new AdharCard(adharno));
		if (idcard == null)
			throw new IdCardNotFoundException("IdCard not found with the adharNo :" + adharno);
		else
			return idcard;
	}

	@Override
	public IdCard addIdCard(IdCard idCard) {
		IdCard id = idDao.findByPancard(idCard.getPancard());
		if (id != null)
			throw new IdCardException("Id card already exist with the id : " + idCard.getPancard());
		IdCard id2 = idDao.findByAdharcard(idCard.getAdharcard());
		if (id2 != null)
			throw new IdCardException("Id card already exist with the id : " + idCard.getAdharcard());

		return idDao.save(idCard);
	}

}