package cz.dusanrychnovsky.util.collections.graphs;

import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

/**
 * Represents a tree with nodes holding values of type V and connected via edges with labels of type L.
 *
 * @param <V>
 * @param <L>
 */
public class Tree<V, L> {
	
	private final Supplier<V> supplier;
	private Node<V, L> rootNode = null;
	
	/**
	 * 
	 * @param supplier supplies initial node values
	 */
	public Tree(Supplier<V> supplier) {
		this.supplier = supplier;
		rootNode = new Node<>(supplier.get());
	}
	
	/**
	 * Returns a node corresponding to the given path or null, if such a node does not exist.
     *
	 * @param path
	 * @return
	 */
	public Node<V, L> get(List<L> path) {
		
		Iterator<L> it = path.iterator();
		Node<V, L> currNode = rootNode;
		
		while (it.hasNext() && currNode != null) {
			L label = it.next();
			currNode = currNode.getChildNode(label);
		}
		
		return currNode;
	}
	
	/**
	 * Inserts a new node with the given value into the represented tree at the given path.
     *
	 * @param path
	 * @param value
	 * @return
	 */
	public Node<V, L> insert(List<L> path, V value) {

		Iterator<L> it = path.iterator();
		Node<V, L> currNode = rootNode;
		
		while (it.hasNext()) {			
			L label = it.next();
			
			Node<V, L> nextNode = currNode.getChildNode(label);
			if (nextNode == null) {
				
				if (it.hasNext()) {
					nextNode = new Node<>(supplier.get());
				}
				else {
					nextNode = new Node<>(value);
				}
			}
			
			currNode = nextNode;
		}
		
		return currNode;
	}
}
