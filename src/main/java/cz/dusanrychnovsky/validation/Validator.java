package cz.dusanrychnovsky.validation;

public abstract class Validator<T> {
	
	/**
	 * 
	 * @param instance
	 * @return
	 */
	public abstract Errors validate(T instance);
	
	/**
	 * 
	 * @param instance
	 * @return
	 */
	public boolean isValid(T instance) {
		return validate(instance).isEmpty();
	}
}
