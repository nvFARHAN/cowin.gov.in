package com.masai.service;

import com.masai.exceptions.IdCardException;
import com.masai.exceptions.IdCardNotFoundException;
import com.masai.model.IdCard;

public interface IdCardService {

	public IdCard getIdcardByPanNo(String panNo,String key) throws IdCardNotFoundException;

	public IdCard getIdCardByAdharNo(Long adharno,String key) throws IdCardNotFoundException;

	public IdCard addIdCard(IdCard idCard) throws IdCardException;
}
