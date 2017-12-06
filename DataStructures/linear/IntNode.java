package linear;

public class IntNode {
	
	int data;
	int count; //how many integers are in the linked list
	IntNode next;
	
	public IntNode(int data, IntNode next){
		this.data = data;
		this.next = next;
	}
	public String toString(){
		return data+"";
	}
}
