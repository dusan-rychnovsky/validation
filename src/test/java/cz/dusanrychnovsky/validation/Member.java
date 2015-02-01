package cz.dusanrychnovsky.validation;

public class Member {

	public static final String FIRSTNAME = "firstName";
	public static final String LASTNAME = "lastName";
	public static final String EMAIL = "email";
	
	private final String firstName;
	private final String lastName;
	private final ContactEmail email;
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 */
	public Member(String firstName, String lastName, ContactEmail email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
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
	public ContactEmail getEmail() {
		return email;
	}
}
