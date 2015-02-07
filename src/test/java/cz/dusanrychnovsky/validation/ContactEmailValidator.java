package cz.dusanrychnovsky.validation;

import cz.dusanrychnovsky.validation.validators.EmailValidator;
import cz.dusanrychnovsky.validation.validators.Validator;

import static cz.dusanrychnovsky.validation.ContactEmail.*;

import static java.util.Arrays.asList;

public class ContactEmailValidator extends Validator<ContactEmail> {
	
	private EmailValidator emailValidator = new EmailValidator();
	private DateRangeValidator dateRangeValidator = new DateRangeValidator();
	
	@Override
	public Errors validate(ContactEmail instance) {
	
		Errors result = new Errors();
		
		result.addAll(EMAIL, emailValidator.validate(instance.getEmail()));
		
		DateRange dateRange = new DateRange(instance.getStartDate(), instance.getEndDate());
		result.addAll(
			asList(
				new Path(START_DATE),
				new Path(END_DATE)
			),
			dateRangeValidator.validate(dateRange)
		);
		
		return result;
	}
}
