package com.cg;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cg.beans.Platform;

public class PlatformValidator implements Validator{

	@Override
	public boolean supports(Class<?> classObject) {
		return Platform.class == classObject;
	}

	@Override
	public void validate(Object target, Errors errors) {

		Platform platform = (Platform)target;
		if (platform == null){
			errors.reject("platform.null", "Platform object is null");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
	}

}