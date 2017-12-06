package linear;

public class Node<T> {   // Node class has a generic type parameter T
	public T data;
	public Node<T> next;
	public Node(T data, Node<T> next) { // constructor name does NOT have a <T> next to it
		this.data = data;
		this.next = next;
	}

	public String toString() {
		return data.toString();
	}
}

/*class DLLNode<T> {
    public T data;
    public DLLNode<T> prev, next;
    public DLLNode(T data, DLLNode<T> next, DLLNode<T> prev) {
        this.data = data; this.next = next; this.prev = prev;
    }
 }*/
