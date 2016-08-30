package com.cg;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cg.beans.Book;

public class BookValidator implements Validator{

	@Override
	public boolean supports(Class<?> classObject) {
		return classObject == Book.class;
	}

	@Override
	public void validate(Object object, Errors errors) {

		Book book = (Book)object;
		if (book.getName() == null || book.getName().trim().length() == 0){
			errors.rejectValue("name", "name.required", "Name field is missing");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "author.required", "Author field is mandatory");

		int pages = book.getNoOfPages();
		if (pages <= 0){
			errors.rejectValue("noOfPages", "noOfPages.incorrect", "No of pages has an incorrect value (zero or -ve)");
		}
	}
}

