package cz.dusanrychnovsky.validation;

import java.time.LocalDate;

public class ContactEmail {
	
	// TODO: can't this be done via retrospection?
	public static final String EMAIL = "email";
	public static final String START_DATE = "startDate";
	public static final String END_DATE = "endDate";
	
	private final String email;
	private final LocalDate startDate;
	private final LocalDate endDate;
	
	/**
	 * 
	 * @param email
	 * @param startDate
	 * @param endDate
	 */
	public ContactEmail(String email, LocalDate startDate, LocalDate endDate) {
		this.email = email;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 
	 * @return
	 */
	public LocalDate getStartDate() {
		return startDate;
	}
	
	/**
	 * 
	 * @return
	 */
	public LocalDate getEndDate() {
		return endDate;
	}
}
