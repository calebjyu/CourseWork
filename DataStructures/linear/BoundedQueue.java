package linear;

import java.util.NoSuchElementException;

public class BoundedQueue<T> {

	private T[] items;
	private int front;
	private int rear;
	private int size;
	
	@SuppressWarnings("unchecked")//eclipse added this
	public BoundedQueue(int bound) {
		items = (T[])new Object[bound]; // NOTE THIS SYNTAX to create a generic array
		size = 0;
		
		// these two initial values will work even if bound = 1 
		front = 0;
		rear = -1;
	}
	
	public void enqueue(T item) 
	throws QueueFullException {
		if (size == items.length) {
			throw new QueueFullException();
		}
		rear = (rear+1) % items.length;
		items[rear] = item;
		size++;
	}
	
	public T dequeue() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		T temp = items[front];
		front = (front+1) % items.length;
		size--;
		if (size == 0) {
			front = 0;
			rear = -1;
		}
		return temp;
	}
	
	public T peek() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return items[front];
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		front = 0;
		rear = -1;
		size = 0;
	}
}

class QueueFullException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;//eclipse added this

	public QueueFullException() {
		super();  // call superclass (Exception) constructor that does not take arguments
	}
	
	public QueueFullException(String message) {
		super(message);  // call superclass (Exception) constructor that takes a single String argument
	}
}