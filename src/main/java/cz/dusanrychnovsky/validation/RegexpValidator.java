package cz.dusanrychnovsky.validation;

import static cz.dusanrychnovsky.validation.ErrorMessage.VALUE_DOES_NOT_MATCH_REGEXP;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Dušan Rychnovský
 *
 */
public class RegexpValidator extends Validator<String> {
	
	private final Error error = new Error(VALUE_DOES_NOT_MATCH_REGEXP);
	private final Pattern pattern;
	
	/**
	 * 
	 * @param regexp
	 */
	public RegexpValidator(String regexp) {
		this.pattern = Pattern.compile(regexp);
	}

	@Override
	public Errors validate(String instance) {
		
		Errors result = new Errors();
		
		Matcher matcher = pattern.matcher(instance);
		if (!matcher.matches()) {
			result.add(error);
		}
		
		return result;
	}
}
