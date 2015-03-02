package cz.dusanrychnovsky.validation.validators;

import cz.dusanrychnovsky.validation.*;
import cz.dusanrychnovsky.validation.Error;

import static cz.dusanrychnovsky.validation.ErrorMessage.VALUE_IS_EMPTY;

public class NonEmptyValidator implements Validator<String> {
	
	private final cz.dusanrychnovsky.validation.Error error = new Error(VALUE_IS_EMPTY);

	@Override
	public Errors validate(String instance) {
		
		Errors result = new Errors();
		
		if (instance == null || instance.trim().isEmpty()) {
			result.add(error);
		}
		
		return result;
	}
}
