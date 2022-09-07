package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.masai.exceptions.IdCardException;
import com.masai.exceptions.IdCardNotFoundException;
import com.masai.exceptions.MemberNotFoundException;
import com.masai.model.AdharCard;
import com.masai.model.CurrentAdminSession;
import com.masai.model.IdCard;
import com.masai.model.PanCard;
import com.masai.repository.AdminSessionDAO;
import com.masai.repository.IdCardDao;

@Service
public class IdCardServiceImpl implements IdCardService {

	@Autowired
	private IdCardDao idDao;

	@Autowired
	private AdminSessionDAO adminSessionDAO;
	
	@Override
	public IdCard getIdcardByPanNo(String panNo,String key) throws MemberNotFoundException {

		 Optional<CurrentAdminSession> optCurrAdmin= adminSessionDAO.findByUuid(key);
			
			if(!optCurrAdmin.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
		
		
		IdCard idcard = idDao.findByPancard(new PanCard(panNo));
		if (idcard == null)
			throw new IdCardNotFoundException("Idcard not found with the  panNo:" + panNo);
		else
			return idcard;
	}

	@Override
	public IdCard getIdCardByAdharNo(Long adharno,String key) throws MemberNotFoundException {

		 Optional<CurrentAdminSession> optCurrAdmin= adminSessionDAO.findByUuid(key);
			
			if(!optCurrAdmin.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
		
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