package cz.dusanrychnovsky.validation.client;

import cz.dusanrychnovsky.validation.ContactEmail;
import cz.dusanrychnovsky.validation.Errors;
import cz.dusanrychnovsky.validation.Member;
import cz.dusanrychnovsky.validation.MemberValidator;
import cz.dusanrychnovsky.validation.validators.Validator;

import java.io.IOException;
import java.time.LocalDate;
import static java.util.Arrays.asList;

public class SampleClient {

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        final LocalDate THREE_DAYS_AGO = LocalDate.now().minusDays(3);
        final LocalDate ONE_DAY_AGO = LocalDate.now().minusDays(1);

        Member member = new Member(
            "John",
            "",
           asList(
               new ContactEmail("john.snow@gmail.com", THREE_DAYS_AGO, ONE_DAY_AGO),
               new ContactEmail("john.snow@outlook.com", ONE_DAY_AGO, THREE_DAYS_AGO),
               new ContactEmail("john.snow@invalid", THREE_DAYS_AGO, ONE_DAY_AGO)
           )
        );

        Validator<Member> validator = new MemberValidator();
        Errors errors = validator.validate(member);

        ViewErrors view = new ViewErrors(member, errors);
        view.print(System.out);
    }
}
