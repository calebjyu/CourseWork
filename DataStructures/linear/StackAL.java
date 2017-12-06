package linear;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class StackAL<T> { //PUSH METHOD IS ORDER O(n) FOR ARRAYLIST STACK... USE Stack.java
	
	ArrayList<T> items;

	public StackAL() {
		items = new ArrayList<T>();  // initial capacity is default
	}

	public StackAL(int cap) {
		items = new ArrayList<T>(cap);  // initial capacity is cap
	}
	
	public void push(T item) {
		items.add(item);   // add to end, top is the end of the array list
	}

	public T pop() 
	throws NoSuchElementException {
		try {
			return items.remove(items.size()-1);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchElementException();
		}
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}

	public int size() {
		return items.size();
	}

	public void clear() {
		items.clear();
	}
	
	public T peek() 
	throws NoSuchElementException {  // peek at the top of the stack
		try {
			return items.get(items.size()-1);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchElementException();
		}
	}

}
