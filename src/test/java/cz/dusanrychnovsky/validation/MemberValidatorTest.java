package cz.dusanrychnovsky.validation;

import static cz.dusanrychnovsky.validation.Member.*;
import static cz.dusanrychnovsky.validation.ContactEmail.*;
import static cz.dusanrychnovsky.validation.ErrorMessage.*;

import java.time.LocalDate;
import static java.util.Arrays.asList;

import static org.junit.Assert.*;
import org.junit.Test;

public class MemberValidatorTest {

    private static final LocalDate THREE_DAYS_AGO = LocalDate.now().minusDays(3);
    private static final LocalDate ONE_DAY_AGO = LocalDate.now().minusDays(1);

	private final MemberValidator validator = new MemberValidator();

	@Test
	public void acceptsAValidMember() {
		
		Member validMember = new Member(
			"John",
			"Snow",
			new ContactEmail("john.snow@gmail.com", THREE_DAYS_AGO, ONE_DAY_AGO)
		);
		
		Errors errors = validator.validate(validMember);
		assertTrue(errors.isEmpty());
	}

    // ========================================================================
    // BASIC VALIDATIONS
    // ========================================================================

    @Test
    public void aMemberWithAnEmptyFirstNameIsRejected() {

        Member aMemberWithAnEmptyFirstName = new Member(
            "",
            "Snow",
            new ContactEmail("john.snow@gmail.com", THREE_DAYS_AGO, ONE_DAY_AGO)
        );

        Errors errors = validator.validate(aMemberWithAnEmptyFirstName);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(new Error(FIRSTNAME, VALUE_IS_EMPTY)));
    }

    @Test
    public void aMemberWithANullFirstNameIsRejected() {

        Member aMemberWithANullFirstName = new Member(
            null,
            "Snow",
            new ContactEmail("john.snow@gmail.com", THREE_DAYS_AGO, ONE_DAY_AGO)
        );

        Errors errors = validator.validate(aMemberWithANullFirstName);
        assertEquals(1, errors.size());
        assertTrue(errors.contains(new Error(FIRSTNAME, VALUE_IS_EMPTY)));
    }

    // ========================================================================
    // LIST VALIDATIONS
    // ========================================================================

    @Test
    public void aMemberWithAnInvalidEmailAddressIsRejected() {

        Member aMemberWithAnInvalidEmailAddress = new Member(
            "John",
            "Snow",
            asList(
                new ContactEmail("john.snow@gmail.com", THREE_DAYS_AGO, ONE_DAY_AGO),
                new ContactEmail("invalid-email-address", THREE_DAYS_AGO, ONE_DAY_AGO)
            )
        );

        Errors errors = validator.validate(aMemberWithAnInvalidEmailAddress);
        assertEquals(1, errors.size());
        assertTrue(
            errors.contains(
                new Error(
                    new Path(EMAILS, "1", EMAIL),
                    EMAIL_IS_MALFORMED
                )
            )
        );
    }

    @Test
    public void aMemberWithAnInvalidEmailAddressAndAnInvalidEmailDateRangeIsRejected() {

        Member aMemberWithAnInvalidEmailAddressAndAnInvalidEmailDateRange = new Member(
            "John",
            "Snow",
            asList(
                new ContactEmail("john.snow@gmail.com", ONE_DAY_AGO, THREE_DAYS_AGO),
                new ContactEmail("invalid-email-address", THREE_DAYS_AGO, ONE_DAY_AGO)
            )
        );

        Errors errors = validator.validate(aMemberWithAnInvalidEmailAddressAndAnInvalidEmailDateRange);
        assertEquals(2, errors.size());

        assertTrue(
            errors.contains(
                new Error(
                    asList(
                        new Path(EMAILS, "0", START_DATE),
                        new Path(EMAILS, "0", END_DATE)
                    ),
                    DATE_RANGE_IS_INVALID
                )
            )
        );

        assertTrue(
            errors.contains(
                new Error(
                    new Path(EMAILS, "1", EMAIL),
                    EMAIL_IS_MALFORMED
                )
            )
        );
    }
}
