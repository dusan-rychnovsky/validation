package cz.dusanrychnovsky.validation;

import static cz.dusanrychnovsky.validation.ErrorMessage.VALUE_IS_EMPTY;

public class NonEmptyValidator extends Validator<String> {
	
	private final Error error = new Error(VALUE_IS_EMPTY);

	@Override
	public Errors validate(String instance) {
		
		Errors result = new Errors();
		
		if (instance == null || instance.trim().isEmpty()) {
			result.add(error);
		}
		
		return result;
	}
}
