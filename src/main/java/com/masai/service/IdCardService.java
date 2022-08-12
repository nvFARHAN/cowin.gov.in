package com.masai.service;

import com.masai.exceptions.MemberNotFoundException;
import com.masai.model.IdCard;

public interface IdCardService {

	public IdCard getIdcardByPanNo(String panNo) throws  MemberNotFoundException;

	public IdCard getIdCardByAdharNo(Long adharno) throws  MemberNotFoundException;

	public IdCard addIdCard(IdCard idCard);
}
