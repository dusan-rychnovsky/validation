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
		
		result.addAll(validateEmail(EMAIL, instance.getEmail()));
		
		return result;
	}
	
	/**
	 * 
	 * @param fieldName
	 * @param instance
	 * @return
	 */
	private Errors validateEmail(String fieldName, ContactEmail instance) {
		
		Errors result = new Errors();
		
		result.addAll(fieldName, nonNullValidator.validate(instance));
		
		if (instance != null) {
			result.addAll(
				fieldName, 
				contactEmailValidator.validate(instance)
			);
		}
		
		return result;
	}
}
