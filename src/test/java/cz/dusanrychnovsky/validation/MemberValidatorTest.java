package cz.dusanrychnovsky.validation;

import static cz.dusanrychnovsky.validation.Member.*;
import static cz.dusanrychnovsky.validation.ContactEmail.*;
import static cz.dusanrychnovsky.validation.ErrorMessage.*;

import java.time.LocalDate;
import static java.util.Arrays.asList;

import static org.junit.Assert.*;
import org.junit.Test;

public class MemberValidatorTest {
	
	private final MemberValidator validator = new MemberValidator();
	
	@Test
	public void acceptsAValidMember() {
		
		Member validMember = new Member(
			"John",
			"Snow",
			new ContactEmail(
				"john.snow@gmail.com",
				LocalDate.now().minusDays(3),
				LocalDate.now().minusDays(1)
			)
		);
		
		Errors errors = validator.validate(validMember);
		assertTrue(errors.isEmpty());
	}
	
	@Test
	public void rejectsAMemberWithoutContactEmail() {
		
		Member memberWithoutContactEmail = new Member("John", "Snow", null);
		Errors errors = validator.validate(memberWithoutContactEmail);
		
		assertEquals(1, errors.size());
		assertTrue(errors.contains(new Error(Member.EMAIL, VALUE_IS_NULL)));
	}
	
	@Test
	public void rejectsAMemberWithAnEmptyFirstnameAndInvalidEmailAddress() {
		
		Member memberWithEmptyFirstnameAndInvalidEmailAddress = new Member(
			"",
			"Snow", 
			new ContactEmail(
				"john.snow@gmail",
				LocalDate.now().minusDays(3),
				LocalDate.now().minusDays(1)
			)
		);
		
		Errors errors = validator.validate(memberWithEmptyFirstnameAndInvalidEmailAddress);
		assertEquals(2, errors.size());
		
		assertTrue(errors.contains(new Error(FIRSTNAME, VALUE_IS_EMPTY)));
		assertTrue(
			errors.contains(
				new Error(
					new Path(Member.EMAIL, ContactEmail.EMAIL), 
					EMAIL_IS_MALFORMED
				)
			)
		);
	}
	
	@Test
	public void rejectsAMemberWithAnInvalidEmailDateRange() {
		
		Member memberWithInvalidEmailDateRange = new Member(
			"John",
			"Snow",
			new ContactEmail(
				"john.snow@gmail.com",
				LocalDate.now().minusDays(1),
				LocalDate.now().minusDays(3)
			)
		);
		
		Errors errors = validator.validate(memberWithInvalidEmailDateRange);
		assertEquals(1, errors.size());
		
		assertTrue(
			errors.contains(
				new Error(
					asList(
						new Path(Member.EMAIL, START_DATE),
						new Path(Member.EMAIL, END_DATE)
					),
					DATE_RANGE_IS_INVALID
				)
			)
		);
	}
}
