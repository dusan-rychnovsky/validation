package cz.dusanrychnovsky.validation.validators;

import cz.dusanrychnovsky.validation.*;
import cz.dusanrychnovsky.validation.Error;

import static cz.dusanrychnovsky.validation.ErrorMessage.VALUE_IS_NULL;

public class NonNullValidator implements Validator<Object> {
	
	private final cz.dusanrychnovsky.validation.Error error = new Error(VALUE_IS_NULL);

	@Override
	public Errors validate(Object instance) {
		
		Errors result = new Errors();
		
		if (instance == null) {
			result.add(error);
		}
		
		return result;
	}
}
