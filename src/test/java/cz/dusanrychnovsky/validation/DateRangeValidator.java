package cz.dusanrychnovsky.validation;

import static cz.dusanrychnovsky.validation.ErrorMessage.DATE_RANGE_IS_INVALID;

import java.time.LocalDate;

public class DateRangeValidator extends Validator<DateRange> {
	
	private final Error error = new Error(DATE_RANGE_IS_INVALID);
	
	@Override
	public Errors validate(DateRange instance) {

		Errors result = new Errors();
		
		LocalDate startDate = instance.getStartDate();
		LocalDate endDate = instance.getEndDate();
		
		if (!startDate.isBefore(endDate)) {
			result.add(error);
		}
		
		return result;
	}
}
