package linear;

import java.util.NoSuchElementException;

public class LinkedListApp {

	Queue<Integer> queue = new Queue<Integer>();
	
	public static void main(String[] args){
		IntNode front = null;
		front = addFront(front, 21);
		front = addFront(front, 15);
		front = addFront(front, 12);
		front = addFront(front, 9);
		front = addFront(front, 3);
		traverse(front);
		IntNode front2 = null;
		front2 = addFront(front2, 19);
		front2 = addFront(front2, 12);
		front2 = addFront(front2, 6);
		front2 = addFront(front2, 3);
		front2 = addFront(front2, 2);
		traverse(front2);
		front=commonElements(front,front2);
		traverse(front);
	}
	
	public static IntNode addFront(IntNode front, int item){
		return new IntNode(item, front);
	}
	
	public static IntNode deleteFront(IntNode front){
		if(front!=null){
			return front.next;
		}else{
			return null;
		}
	}
	
	public static void traverse(IntNode front){
		for(IntNode ptr=front; ptr!=null; ptr=ptr.next){
			System.out.print("->");
			System.out.print(ptr.data);
		}
		System.out.println();
	}
	
	public static boolean search(IntNode front, int target){
		for(IntNode ptr=front; ptr!=null; ptr=ptr.next){
			if(target==ptr.data){
				return true;
			}
		}
		return false;
	}
	
	public static void addAfter(IntNode front, int newItem, int afterThis){
		for(IntNode ptr=front; ptr!=null; ptr=ptr.next){
			if(afterThis==ptr.data){
				IntNode tmp = new IntNode(newItem, null);
				tmp.next = ptr.next;
				ptr.next = tmp;
				return;
			}
		}
	}
	
	public static IntNode delete(IntNode front, int item){
		if(front==null){
			return front;
		}
		if(front.data==item){
			return front.next;
		}
		//loop start
		IntNode ptr=front, prev=null;
		while(ptr.data!=item && ptr!=null){
			prev=ptr;
			ptr=ptr.next;
		}
		if(ptr==null){
			return front;
		}
		prev.next = ptr.next;
		return front;
	}
	public static IntNode difference(IntNode frontL1,IntNode frontL2){
		IntNode ptr1=frontL1;
		IntNode ptr2=frontL2;
		IntNode newFront=null;
		while(ptr1!=null&&ptr2!=null){
			if(ptr1.data==ptr2.data){
				ptr1=ptr1.next;
				ptr2=ptr2.next;
			}else if(ptr1.data>ptr2.data){
				if(ptr2.next==null){
					if(newFront==null){
						newFront = new IntNode(ptr1.data,null);
					}else if(newFront.next==null){
						newFront.next=new IntNode(ptr1.data,null);
					}else{
						IntNode ptr=newFront;
						while(ptr.next!=null){
							ptr=ptr.next;
						}
						ptr.next=new IntNode(ptr1.data,null);
					}
					ptr1=ptr1.next;
				}
				ptr2=ptr2.next;
			}else{
				if(newFront==null){
					newFront = new IntNode(ptr1.data,null);
				}else if(newFront.next==null){
					newFront.next=new IntNode(ptr1.data,null);
				}else{
					IntNode ptr=newFront;
					while(ptr.next!=null){
						ptr=ptr.next;
					}
					ptr.next=new IntNode(ptr1.data,null);
				}
				ptr1=ptr1.next;
			}
		}
		return newFront;
	}
	//--------------Problem Set 2 #1
	public static IntNode addBefore(IntNode front, int target, int newItem){
		if(front==null){
			return front;
		}
		IntNode prev=null;
		for(IntNode ptr=front;ptr!=null;ptr=ptr.next){
			if(target==ptr.data){
				if(prev==null){
					return new IntNode(newItem,ptr);
				}else{
					prev.next=new IntNode(newItem,ptr);
					return front;
				}
			}
			prev=ptr;
		}
		return front; //if not found
	}
	//--------------Problem Set 2 #5
	public static StringNode deleteAllOccurrences(StringNode front, String target) {
		if(front==null){
			return front;
		}
		StringNode prev = null;
		for(StringNode ptr=front;ptr!=null;ptr=ptr.next){
			if(target==ptr.data){
				if(prev==null){
					ptr=front.next;
				}else{
					prev.next=ptr.next;
				}
			}
			prev=ptr;
		}
		return front;
	}
	//--------------Problem Set 2 #6
	// creates a new linked list consisting of the items common to the input lists
    // returns the front of this new linked list, null if there are no common items
    public static IntNode commonElements(IntNode frontL1, IntNode frontL2) {
    	IntNode newFront = null;
    	IntNode ptr1 = frontL1;
    	IntNode ptr2 = frontL2;
    	while(ptr1!=null&&ptr2!=null){
    		if(ptr1.data==ptr2.data){
    			if(newFront==null){
					newFront = new IntNode(ptr1.data,null);
				}else if(newFront.next==null){
					newFront.next=new IntNode(ptr1.data,null);
				}else{
					IntNode ptr=newFront;
					while(ptr.next!=null){
						ptr=ptr.next;
					}
					ptr.next=new IntNode(ptr1.data,null);
				}
				ptr1=ptr1.next;
				ptr2=ptr2.next;
    		}else if(ptr1.data>ptr2.data){
    			ptr2=ptr2.next;
    		}else{
    			ptr1=ptr1.next;
    		}
    	}
    	return newFront;
       
    }
	
	//--------------Problem Set 3 #4
	public static DLLNode reverse(DLLNode front){
		DLLNode ptr = front;
		if(ptr==null){
			return null;
		}
		if(ptr.next==null){
			return front;
		}
		for(;ptr!=null;ptr=ptr.prev){//goes through whole list
			if(ptr.prev==null){//the beginning of the list
				ptr.prev=ptr.next;
				ptr.next=null;
			}else if(ptr.next==null){//the end of the list
				ptr.next=ptr.prev;
				ptr.prev=null;
				front=ptr;
			}else{
				DLLNode temp = ptr.next;
				ptr.next=ptr.prev;
				ptr.prev=temp;
			}
		}
		return front;
	}
	//--------------Problem Set 3 #7
	public static Node reverse(Node front) {
	    /** COMPLETE THIS RECURSIVE METHOD **/
		if(front==null){
			return null;
		}
		if(front.next==null){
			return front;
		}
		Node rev = reverse(front.next);
	    front.next.next = front;
	    front.next = null;       
	    return rev;
	}
	//--------------Problem Set 5 #1
	// returns the item at the front of the given queue, without 
    // removing it from the queue
    public static <T> T peek(Queue<T> q) 
    throws NoSuchElementException {
    	Queue<T> q2 = new Queue<T>();
    	for(int i=0;i<q.size();i++){
    		q2.enqueue(q.dequeue());
    	}
    	return q2.dequeue();
    }
}

class StringNode {
    public String data;
    public StringNode next;
    public StringNode(String data, StringNode next) {
        this.data = data; this.next = next;
    }
    public String toString() {
        return data;
    }
 }
class DLLNode {
    public String data;
    public DLLNode prev, next;
    public DLLNode(String data, DLLNode next, DLLNode prev) {
        this.data = data; this.next = next; this.prev = prev;
    }
 }
