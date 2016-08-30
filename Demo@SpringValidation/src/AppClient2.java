import java.util.Arrays;
import java.util.List;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;

import com.cg.LicenseTypeValidator;
import com.cg.PlatformValidator;
import com.cg.ProductValidator;
import com.cg.beans.LicenseType;
import com.cg.beans.Platform;
import com.cg.beans.Product;

public class AppClient2 {

	public static void main(String[] args) {

		testSuccess();
		testLicenseTypeNull();
		testPlatforms();
	}

	private static void testSuccess(){

		Product javaProduct = new Product();
		javaProduct.setName("Java");
		javaProduct.setCategory("Programming Language");
		javaProduct.setVersion("7.0");
		javaProduct.setLicenseType(LicenseType.SHAREWARE);

		Platform windowsPlatform = new Platform("Windows/XP");
		javaProduct.getSupportedPlatforms().add(windowsPlatform);

		Platform unixPlatform = new Platform("Unix");
		javaProduct.getSupportedPlatforms().add(unixPlatform);

		validate(javaProduct);
	}

	private static void testLicenseTypeNull(){

		Product javaProduct = new Product();
		javaProduct.setName("Java");
		javaProduct.setCategory("Programming Language");
		javaProduct.setVersion("7.0");

		Platform windowsPlatform = new Platform("Windows/XP");
		javaProduct.getSupportedPlatforms().add(windowsPlatform);

		Platform unixPlatform = new Platform("Unix");
		javaProduct.getSupportedPlatforms().add(unixPlatform);

		validate(javaProduct);
	}

	private static void testPlatforms(){

		Product javaProduct = new Product();
		javaProduct.setName("Java");
		javaProduct.setCategory("Programming Language");
		javaProduct.setVersion("7.0");
		javaProduct.setLicenseType(LicenseType.FREEWARE);

		Platform windowsPlatform = new Platform("Windows/XP");
		javaProduct.getSupportedPlatforms().add(windowsPlatform);

		Platform nullPlatform = new Platform(null);
		javaProduct.getSupportedPlatforms().add(nullPlatform);

		validate(javaProduct);
	}

	private static void validate(Product javaProduct){

		ProductValidator productValidator = new ProductValidator();
		productValidator.setLicenseTypeValidator(new LicenseTypeValidator());
		productValidator.setPlatformValidator(new PlatformValidator());

		BeanPropertyBindingResult result = new BeanPropertyBindingResult(javaProduct, "javaProduct");
		ValidationUtils.invokeValidator(productValidator, javaProduct, result);

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
}