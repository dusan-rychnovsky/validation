package cz.dusanrychnovsky.validation;

import java.time.LocalDate;

public class DateRange {

	private final LocalDate startDate;
	private final LocalDate endDate;
	
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 */
	public DateRange(LocalDate startDate, LocalDate endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
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
