package cz.dusanrychnovsky.util.collections;

import java.util.Iterator;
import java.util.function.Function;

// TODO: come up with a more suitable name
public class CompoundIterator<T, R> implements Iterator<R> {
	
	private final Iterator<T> it;
	private final Function<T, R> func;
	
	/**
	 * 
	 * @param it
	 * @param func
	 */
	public CompoundIterator(Iterator<T> it, Function<T, R> func) {
		this.it = it;
		this.func = func;
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public R next() {
		
		T item = it.next();
		return func.apply(item);
	}
}
