package cz.dusanrychnovsky.util.collections.graphs;

import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

public class Tree<V, L> {
	
	private final Supplier<V> supplier;
	private Node<V, L> rootNode = null;
	
	/**
	 * 
	 * @param supplier
	 */
	public Tree(Supplier<V> supplier) {
		this.supplier = supplier;
		rootNode = new Node<V, L>(supplier.get());
	}
	
	/**
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
	 * 
	 * @param path
	 * @param value
	 * @return
	 */
	// TODO: throw an exception when the insert operation would lead to creating a cycle
	public Node<V, L> insert(List<L> path, V value) {

		Iterator<L> it = path.iterator();
		Node<V, L> currNode = rootNode;
		
		while (it.hasNext()) {			
			L label = it.next();
			
			Node<V, L> nextNode = currNode.getChildNode(label);
			if (nextNode == null) {
				
				if (it.hasNext()) {
					nextNode = new Node<V, L>(supplier.get());
				}
				else {
					nextNode = new Node<V, L>(value);
				}
			}
			
			currNode = nextNode;
		}
		
		return currNode;
	}
}
