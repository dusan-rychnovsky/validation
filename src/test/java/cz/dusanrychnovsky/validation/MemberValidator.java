package cz.dusanrychnovsky.validation;

import static cz.dusanrychnovsky.validation.Member.*;

public class MemberValidator extends Validator<Member> {
	
	private NonEmptyValidator nonEmptyValidator = new NonEmptyValidator();
	private NonNullValidator nonNullValidator = new NonNullValidator();
	private ContactEmailValidator contactEmailValidator = new ContactEmailValidator();
	
	@Override
	public Errors validate(Member instance) {
		
		Errors result = new Errors();
		
		result.addAll(FIRSTNAME, nonEmptyValidator.validate(instance.getFirstName()));
		result.addAll(LASTNAME, nonEmptyValidator.validate(instance.getLastName()));
		
		result.addAll(EMAIL, nonNullValidator.validate(instance.getEmail()));
		result.addAll(EMAIL, contactEmailValidator.validate(instance.getEmail()));
		
		return result;
	}
}
