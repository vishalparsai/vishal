package com.cg;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cg.beans.LicenseType;

public class LicenseTypeValidator implements Validator{

	@Override
	public boolean supports(Class<?> classObject) {
		return LicenseType.class == classObject;
	}

	@Override
	public void validate(Object target, Errors errors) {

		LicenseType licenseType = (LicenseType)target;
		if (licenseType == null){
			errors.reject("licenseType.null", "License type is null");
		}
	}
}