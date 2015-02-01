package cz.dusanrychnovsky.util.collections.graphs;

import java.util.HashMap;
import java.util.Map;

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
