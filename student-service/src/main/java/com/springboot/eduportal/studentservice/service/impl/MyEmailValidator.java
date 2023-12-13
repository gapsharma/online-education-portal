package com.springboot.eduportal.studentservice.service.impl;

import com.networknt.org.apache.commons.validator.routines.EmailValidator;

public class MyEmailValidator  extends EmailValidator {

	public MyEmailValidator(boolean allowLocal) {
		super(allowLocal);
//		super(allowLocal, allowLocal);
//		super(allowLocal, allowLocal, null);
	}

	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		MyEmailValidator mEV = new MyEmailValidator(false);
	}

}
