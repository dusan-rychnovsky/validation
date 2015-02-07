package cz.dusanrychnovsky.util.collections.graphs;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a generic graph node, with a value of type V and a collection of incident edges with labels of type L.
 *
 * @param <V> the type of the value
 * @param <L> the type of edge labels
 */
public class Node<V, L> {
	
	private final V value;
	private Map<L, Node<V, L>> childNodes = new HashMap<>();
	
	/**
	 * 
	 * @param value
	 */
	public Node(V value) {
		this.value = value;
	}
	
	/**
	 * 
	 * @return
	 */
	public V getValue() {
		return value;
	}
	
	/**
	 *
	 * @param label
	 * @return
	 */
	public Node<V, L> getChildNode(L label) {
		return childNodes.get(label);
	}
}
