package cz.dusanrychnovsky.validation.validators;

import cz.dusanrychnovsky.validation.Errors;

/**
 * Represents an object-graph validator.
 *
 * @param <T>
 */
public interface Validator<T> {
	
	/**
	 *  Validates the given object-graph and returns a collection of all applicable errors.
     *
	 * @param instance
	 * @return
	 */
	public abstract Errors validate(T instance);
	
	/**
	 * Returns true if and only if the given object-graph is valid, i.e. there are no errors applicable to it.
     *
	 * @param instance
	 * @return
	 */
	default public boolean isValid(T instance) {
		return validate(instance).isEmpty();
	}
}
