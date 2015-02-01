package cz.dusanrychnovsky.validation;

import static cz.dusanrychnovsky.validation.ErrorMessage.VALUE_IS_NULL;

public class NonNullValidator extends Validator<Object> {
	
	private final Error error = new Error(VALUE_IS_NULL);

	@Override
	public Errors validate(Object instance) {
		
		Errors result = new Errors();
		
		if (instance == null) {
			result.add(error);
		}
		
		return result;
	}
}
