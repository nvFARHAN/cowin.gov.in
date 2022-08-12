package com.masai.service;

import com.masai.model.IdCard;

public interface IdCardService {

	public IdCard getIdcardByPanNo(String panNo);

	public IdCard getIdCardByAdharNo(Long adharno);

	public IdCard addIdCard(IdCard idCard);
}
