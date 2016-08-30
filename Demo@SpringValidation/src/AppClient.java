
import java.util.Arrays;
import java.util.List;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;

import com.cg.BookValidator;
import com.cg.beans.Book;

public class AppClient {

	public static void main(String[] args) {

		testNameEmpty();
		testNoOfPages();
	}

	private static void testNameEmpty(){

		BookValidator bookValidator = new BookValidator();
		Book bookObject = new Book();
		bookObject.setNoOfPages(10);

		BeanPropertyBindingResult result = new BeanPropertyBindingResult(bookObject, "book");
		ValidationUtils.invokeValidator(bookValidator, bookObject, result);

		System.out.println("Total error count is " + result.getErrorCount());
		System.out.println("-------------------------------------------------");

		List<ObjectError> allObjectErrors = result.getAllErrors();
		for (ObjectError objectError : allObjectErrors){

			if (objectError instanceof FieldError){

				FieldError fieldError = (FieldError)objectError;
				System.out.println("Field name is " + fieldError.getField());
			}

			System.out.println("Codes " + Arrays.asList(objectError.getCodes()).toString());
			System.out.println("Error Code is " + objectError.getCode());
			System.out.println("Default message is " + objectError.getDefaultMessage());
			System.out.println();
		}
		System.out.println("-------------------------------------------------");
	}

	private static void testNoOfPages(){

		BookValidator bookValidator = new BookValidator();
		Book bookObject = new Book();
		bookObject.setName("Programming in Java");
		bookObject.setAuthor("Some Author");
		bookObject.setNoOfPages(200);
		
		BeanPropertyBindingResult result = new BeanPropertyBindingResult(bookObject, "book");
		ValidationUtils.invokeValidator(bookValidator, bookObject, result);

		System.out.println("Total error count is " + result.getErrorCount());
		System.out.println("-------------------------------------------------");

		List<ObjectError> allObjectErrors = result.getAllErrors();
		for (ObjectError objectError : allObjectErrors){

			if (objectError instanceof FieldError){

				FieldError fieldError = (FieldError)objectError;
				System.out.println("Field name is " + fieldError.getField());
			}

			System.out.println("Error Code is " + objectError.getCode());
			System.out.println("Default message is " + objectError.getDefaultMessage());
			System.out.println();
		}
		System.out.println("-------------------------------------------------");
	}
}