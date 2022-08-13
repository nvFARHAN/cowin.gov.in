package com.masai.service;

import com.masai.exceptions.IdCardException;
import com.masai.exceptions.IdCardNotFoundException;
import com.masai.model.IdCard;

public interface IdCardService {

	public IdCard getIdcardByPanNo(String panNo) throws IdCardNotFoundException;

	public IdCard getIdCardByAdharNo(Long adharno) throws IdCardNotFoundException;

	public IdCard addIdCard(IdCard idCard) throws IdCardException;
}
