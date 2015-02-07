package cz.dusanrychnovsky.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Represents a single error.
 *
 * An error comprises of an error message and a (possibly empty) set of paths. An error always needs to be related to a
 * base object-graph. The paths denote fields in that graph for which the represented error applies.
 *
 * Note that a single error can be applicable for more than one field - for example, an error condition like "start-date
 * must not precede end-date" relates to fields startDate and endDate.
 *
 * @author Dušan Rychnovský
 *
 */
public class Error {

    // TODO: while an enum improves compile-time safety, it also ties the framework to a specific application
    // (maybe use an interface here and have an application specific enum implement it)
	private final ErrorMessage message;

    // TODO: should in fact be a set, not a list
    private final List<Path> fieldPaths;
	
	/**
	 * 
	 * @param fieldPaths
	 * @param message
	 */
	public Error(List<Path> fieldPaths, ErrorMessage message) {
		this.fieldPaths = new ArrayList<>(fieldPaths);
		this.message = message;
	}

	/**
	 * 
	 * @param fieldPath
	 * @param message
	 */
	public Error(Path fieldPath, ErrorMessage message) {
		this(Collections.singletonList(fieldPath), message);
	}
	
	/**
	 * 
	 * @param fieldName
	 * @param message
	 */
	public Error(String fieldName, ErrorMessage message) {
		this(asList(new Path(fieldName)), message);
	}
	
	/**
	 * 
	 * @param message
	 */
	public Error(ErrorMessage message) {
		this(Collections.<Path>emptyList(), message);
	}
	
	/**
	 * 
	 * @param basePaths
	 * @return
	 */
	public Error prependPaths(List<Path> basePaths) {
		
		if (fieldPaths.isEmpty()) {
			return new Error(basePaths, message);
		}
		
		if (basePaths.size() == 1) {
			
			Path basePath = basePaths.get(0);
			
			List<Path> newPaths = new ArrayList<>();
			for (Path path : fieldPaths) {
				newPaths.add(path.prepend(basePath));
			}
			
			return new Error(newPaths, message);
		}

		throw new IllegalArgumentException(
			"Cannot prepend more than one path."
		);
	}
	
	/**
	 * 
	 * @return
	 */
	public ErrorMessage getMessage() {
		return message;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Path> getFieldPaths() {
		return fieldPaths;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Error)) {
			return false;
		}
		
		Error other = (Error) obj;
		return
			message.equals(other.message) &&
			fieldPaths.equals(other.fieldPaths);
	}
	
	@Override
	public int hashCode() {
		return message.hashCode() + fieldPaths.hashCode();
	}
}
