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
public class Path {
	
	private final List<String> elements;
	
	/**
	 * 
	 * @param elements
	 */
	public Path(String... elements) {
		this(asList(elements));
	}
	
	/**
	 * 
	 * @param elements
	 */
	public Path(List<String> elements) {
		this.elements = new ArrayList<>(elements);
	}
	
	/**
	 * 
	 * @param other
	 * @return
	 */
	public Path append(Path other) {
		
		List<String> newElements = new ArrayList<>();
		newElements.addAll(elements);
		newElements.addAll(other.elements);
		
		return new Path(newElements);
	}
	
	/**
	 * 
	 * @param other
	 * @return
	 */
	public Path prepend(Path other) {
		return other.append(this);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String> getElements() {
		return Collections.unmodifiableList(elements);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Path)) {
			return false;
		}
		
		Path other = (Path) obj;
		return elements.equals(other.elements);
	}
	
	@Override
	public int hashCode() {
		return elements.hashCode();
	}
}
