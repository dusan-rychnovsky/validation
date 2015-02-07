package cz.dusanrychnovsky.validation;

import cz.dusanrychnovsky.util.collections.graphs.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

import static java.util.Arrays.asList;

/**
 * Represents a collection of errors.
 *
 * @author Dušan Rychnovský
 *
 */
public class Errors implements Iterable<Error> {
	
	private final Tree<List<Error>, String> tree = new Tree<>(() -> new ArrayList<>());
	private final List<Error> errors = new ArrayList<>();
	
	/**
	 * 
	 * @param error
	 */
	public void add(Error error) {

		errors.add(error);
		
		for (Path path : error.getFieldPaths()) {
			
			Node<List<Error>, String> fieldNode = tree.insert(path.getElements(), new ArrayList<Error>());			
			fieldNode.getValue().add(error);
		}
	}
	
	/**
	 * 
	 * @param paths
	 * @param error
	 */
	public void add(List<Path> paths, Error error) {
		
		Error newError = error.prependPaths(paths);
		add(newError);
	}
	
	/**
	 * 
	 * @param errors
	 */
	public void addAll(Errors errors) {
		for (Error error : errors) {
			add(error);
		}
	}
	
	/**
	 * 
	 * @param paths
	 * @param errors
	 */
	public void addAll(List<Path> paths, Errors errors) {
		for (Error error : errors) {
			add(paths, error);
		}
	}

	/**
	 * 
	 * @param fieldName
	 * @param errors
	 */
	public void addAll(String fieldName, Errors errors) {
		addAll(asList(new Path(fieldName)), errors);
	}
	
	/**
	 * 
	 * @param fieldPath
	 * @return
	 */
	public List<Error> get(Path fieldPath) {
		
		List<Error> result = new ArrayList<>();
		
		Node<List<Error>, String> node = tree.get(fieldPath.getElements());
		if (node != null) {
			result.addAll(node.getValue());
		}
		
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return errors.isEmpty();
	}
	
	/**
	 * 
	 * @return
	 */
	public int size() {
		return errors.size();
	}
	
	/**
	 * 
	 * @param error
	 * @return
	 */
	public boolean contains(Error error) {
		return errors.contains(error);
	}

	@Override
	public Iterator<Error> iterator() {
		return errors.iterator();
	}
}
