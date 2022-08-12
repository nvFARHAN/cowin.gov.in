package com.masai.exceptions;

public class MemberNotFoundException extends RuntimeException{
	
	public MemberNotFoundException() {
		
	}
	
public MemberNotFoundException(String Message) {
		super(Message);
	}

}
