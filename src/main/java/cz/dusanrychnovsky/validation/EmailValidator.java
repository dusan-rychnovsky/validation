package cz.dusanrychnovsky.validation;

import static cz.dusanrychnovsky.validation.ErrorMessage.EMAIL_IS_MALFORMED;

/**
 * 
 * @author Dušan Rychnovský
 *
 */
public class EmailValidator extends Validator<String> {
	
	private static final String EMAIL_REGEXP = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
	
	private final RegexpValidator regexpValidator = new RegexpValidator(EMAIL_REGEXP);
	private final Error error = new Error(EMAIL_IS_MALFORMED);
	
	@Override
	public Errors validate(String instance) {
		
		Errors result = new Errors();
		
		if (!regexpValidator.isValid(instance)) {
			result.add(error);
		}
		
		return result;
	}
}
