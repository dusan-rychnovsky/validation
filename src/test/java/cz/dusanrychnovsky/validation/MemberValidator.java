package cz.dusanrychnovsky.validation;

import cz.dusanrychnovsky.validation.validators.NonEmptyValidator;
import cz.dusanrychnovsky.validation.validators.Validator;

import static cz.dusanrychnovsky.validation.Member.*;

public class MemberValidator extends Validator<Member> {
	
	private NonEmptyValidator nonEmptyValidator = new NonEmptyValidator();
	private ListValidator<ContactEmail> contactEmailsValidator = new ListValidator<>(new ContactEmailValidator());
	
	@Override
	public Errors validate(Member instance) {
		
		Errors result = new Errors();
		
		result.addAll(FIRSTNAME, nonEmptyValidator.validate(instance.getFirstName()));
		result.addAll(LASTNAME, nonEmptyValidator.validate(instance.getLastName()));
		result.addAll(EMAILS, contactEmailsValidator.validate(instance.getEmails()));
		
		return result;
	}
}
