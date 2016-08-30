package com.cg;

import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cg.beans.Platform;
import com.cg.beans.Product;

public class ProductValidator implements Validator{

	private LicenseTypeValidator licenseTypeValidator;
	private PlatformValidator platformValidator;

	public void setLicenseTypeValidator(LicenseTypeValidator licenseTypeValidator){
		this.licenseTypeValidator = licenseTypeValidator;
	}

	public void setPlatformValidator(PlatformValidator platformValidator){
		this.platformValidator = platformValidator;
	}

	@Override
	public boolean supports(Class<?> classObject) {
		return classObject == Product.class;
	}

	@Override
	public void validate(Object object, Errors errors) {

		Product product = (Product)object;
		if (product == null){
			errors.reject("product.null", "Product is null");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required", "Name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "category.required", "Category is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "version", "version.required", "Version is required");

		// For License type
		try{
			ValidationUtils.invokeValidator(licenseTypeValidator, product.getLicenseType(), errors);
		}catch (Exception e){
			e.printStackTrace();
		}

		// For platforms
		Set<Platform> allPlatforms = product.getSupportedPlatforms();
		if (allPlatforms == null || allPlatforms.size() == 0){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "supportedPlatforms", "supportedPlatforms.required", "Platforms is required");
		}else{
			try{
				for (Platform platform : allPlatforms){
					ValidationUtils.invokeValidator(platformValidator, platform, errors);
				}
			}catch (Exception exception){
				exception.printStackTrace();
			}
		}
	}
}