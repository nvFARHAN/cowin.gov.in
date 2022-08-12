package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.MemberNotFoundException;
import com.masai.model.AdharCard;
import com.masai.model.IdCard;
import com.masai.model.PanCard;
import com.masai.repository.AdharcardDao;
import com.masai.repository.IdCardDao;
import com.masai.repository.PancardDao;

@Service
public class IdCardServiceImpl implements IdCardService {

	@Autowired
	private IdCardDao idDao;
	
	@Autowired
	private PancardDao panDao;
	
	@Autowired
	private AdharcardDao aDao;

	@Override
	public IdCard getIdcardByPanNo(String panNo) throws  MemberNotFoundException{
		Optional<PanCard> pancard=panDao.findById(panNo);
		if(pancard==null)
			throw new MemberNotFoundException("Member not found  with the panNo:"+panNo);
		else {
			IdCard idcard=idDao.findByPancard(pancard);
			if(idcard==null)
				throw new MemberNotFoundException("Member not found idcard with the  panNo:"+panNo);
			else
			return idcard;
		}
	}

	@Override
	public IdCard getIdCardByAdharNo(Long adharno) throws  MemberNotFoundException{
		AdharCard adharcard=aDao.findAdharcardByadharNo(adharno);
		if(adharcard==null)
			throw new MemberNotFoundException("Member not found  with the panNo:"+adharno);
		else {
		IdCard idcard=idDao.findByAdharcard(adharcard);
		if(idcard==null)
			throw new MemberNotFoundException("Member not found  with the adharNo id:"+adharno);
		else 
		return idcard;
		}
	}

	@Override
	public IdCard addIdCard(IdCard idCard) {
		Optional<IdCard> opt = idDao.findById(idCard.getId());
		if (opt.isPresent())
			return idDao.save(idCard);
		else
			throw new RuntimeException("id card is already present with same id");
	}

}
