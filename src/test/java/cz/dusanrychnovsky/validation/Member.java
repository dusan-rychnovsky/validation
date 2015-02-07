package cz.dusanrychnovsky.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public class Member {

	public static final String FIRSTNAME = "firstName";
	public static final String LASTNAME = "lastName";
	public static final String EMAILS = "emails";
	
	private final String firstName;
	private final String lastName;
	private final List<ContactEmail> emails = new ArrayList<>();
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param emails
	 */
	public Member(String firstName, String lastName, List<ContactEmail> emails) {

		this.firstName = firstName;
		this.lastName = lastName;

        for (ContactEmail email : emails) {
            this.emails.add(email);
        }
	}

    /**
     *
     * @param firstName
     * @param lastName
     * @param emails
     */
    public Member(String firstName, String lastName, ContactEmail... emails) {
        this(firstName, lastName, asList(emails));
    }
	
	/**
	 * 
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<ContactEmail> getEmails() {
		return Collections.unmodifiableList(emails);
	}
}
