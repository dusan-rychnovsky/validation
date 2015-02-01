package cz.dusanrychnovsky.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * 
 * @author Dušan Rychnovský
 *
 */
public class Error {
	
	private final ErrorMessage message;
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
		
		// TODO: will need to think about proper path-prepending semantics
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
