package cz.dusanrychnovsky.validation;

import static cz.dusanrychnovsky.validation.ContactEmail.*;

public class ContactEmailValidator extends Validator<ContactEmail> {
	
	private EmailValidator emailValidator = new EmailValidator();
	private DateRangeValidator dateRangeValidator = new DateRangeValidator();
	
	@Override
	public Errors validate(ContactEmail instance) {
	
		Errors result = new Errors();
		
		result.addAll(EMAIL, emailValidator.validate(instance.getEmail()));
		
		DateRange dateRange = new DateRange(instance.getStartDate(), instance.getEndDate());
		result.addAll(START_DATE, END_DATE, dateRangeValidator.validate(dateRange));
		
		return result;
	}
}
