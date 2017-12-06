package linear;

import java.util.NoSuchElementException;

public class Queue<T> {
	
	private Node<T> rear;
	private int size;
	
	public Queue() {
		rear = null;
		size = 0;
	}

	public void enqueue(T item) {
		Node<T> newItem = new Node<T>(item, null);
		if (rear == null) {
			newItem.next = newItem;
		} else {
			newItem.next = rear.next;
			rear.next = newItem;
		}
		size++;
		rear = newItem;
	}
	
	public T dequeue() 
	throws NoSuchElementException {
		if (rear == null) {
			throw new NoSuchElementException("queue is empty");
		}
		T data = rear.next.data;
		if (rear == rear.next) {
			rear = null;
		} else {
			rear.next = rear.next.next;
		}
		size--;
		return data;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		size = 0;
		rear = null;
	}
	
	public T peek() 
	throws NoSuchElementException {
		if (rear == null) {
			throw new NoSuchElementException("queue is empty");
		}
		return rear.next.data;				
	}
	
	//--------------Problem Set 5 #2
    // extract the even position items from this queue into
    // the result queue, and delete them from this queue
    public Queue<T> evenSplit() {
    	Queue<T> evenQueue = new Queue<T>(); 
        int originalSize = size();// size of this original queue
        // iterate once over each pair of queue elements
        for (int pos=2; pos <= originalSize; pos += 2) {
            // the first in a pair is recycled
            enqueue(dequeue());
            // the second in a pair goes to result queue
            evenQueue.enqueue(dequeue());
        }
        // if original size was an odd number, we need to
        // recycle one more time
        if ((originalSize % 2) == 1) {
            enqueue(dequeue());
        }
        return evenQueue;
    }
}