package tree;

import java.util.ArrayList;
import java.util.NoSuchElementException;

//only allows objects that implement compareTo()
class BSTNode<T extends Comparable<T>>{
	
		T data; BSTNode<T> left, right;
		
		public BSTNode(T data){
			this.data = data;
			left=null;
			right=null;
		}
}

// BST does not allow duplicates
// only allows objects that implement compareTo
public class BST<T extends Comparable<T>> {

	BSTNode<T> root;
	int size;
	
	public BST() {   // constructor initializes tree to empty
		root = null;
		size = 0;
	}
	
	public T search(T key) {
		BSTNode<T> ptr = root;
		while (ptr != null) {
			int c = key.compareTo(ptr.data);
			if (c == 0) {
				return ptr.data;
			}
			/*
			if (c < 0) {
				ptr = ptr.left;
			} else {
				ptr = ptr.right;
			}
			*/
			// this is the same thing as the if-else block, using the TERNARY operator
			ptr = c < 0 ? ptr.left : ptr.right;
		}
		return null;
	}
	
	public void insert(T item) 
	throws IllegalArgumentException {
		BSTNode<T> prev=null, ptr=root;
		int c=0;
		while (ptr != null) {   // search until fail
			c = item.compareTo(ptr.data);
			if (c == 0) { // duplicate, not allowed
				throw new IllegalArgumentException("Duplicates not allowed");
			}
			prev=ptr;
			ptr = c < 0 ? ptr.left : ptr.right;
		}
		// create new node for item
		BSTNode<T> temp = new BSTNode<T>(item);
		if (prev == null) { // tree was empty
			root = temp;
			size = 1;
			return;
		}
		if (c < 0) {
			prev.left = temp;
		} else {
			prev.right = temp;
		}
		size++;
	}
	
	public T delete(T key) 
	throws NoSuchElementException {
		// search and locate
		BSTNode<T> p=null, x=root;
		int c=0;
		T hold=null;
		while (x != null) {   // search until fail
			//c = x.data.compareTo(key);
			c = key.compareTo(x.data);
			if (c == 0) { // found it
				hold = x.data;  // hold on to data for return later
				break;
			}
			p=x;
			x = c < 0 ? x.left : x.right;
		}
		// not found
		if (x == null) {
			throw new NoSuchElementException("key not found");
		}
		
		// check case 3
		if (x.left != null && x.right != null) {
			// find inorder predecessor
			BSTNode<T> y=x.left;
			p = x; // make sure p tags along
			while (y.right != null) {
				p = y;
				y = y.right;
			}
			// copy y's data into x
			x.data = y.data;
			// reset x to y, to be ready to fall through to case 1 or 2
			x = y;
		}
		
		// what follows is case 1 or 2
		// what if p is null? 
		if (p == null) { // works for both x is leaf and x has one child
			root = x.left != null ? x.left : x.right;
			size--;
			return hold;
		}
		
		// leaf node
		//if (x.left == null && x.right == null) {
		/*  WHAT HAPPENS IN THIS IF-ELSE IS THE SAME AS THE IF-ELSE AT THE END
		 * BECAUSE IF x is a leaf, both x.right and x.left will be NULL 
			if (x == p.left) {
				p.left = null;
			} else {
				p.right = null;
			}
			size--;
			return hold;
			*/
		//}
		
		// one child node
		//if ((x.left != null && x.right == null) || (x.left == null && x.right != null)) {
			if (x == p.left) {
				p.left = x.left != null ? x.left : x.right;
			} else {
				p.right = x.left != null ? x.left : x.right;
			}
			size--;
			return hold;
	}
	
	private void inorder(BSTNode<T> root, ArrayList<T> list){
		if(root == null){
			return;
		}

		inorder(root.left,list);
		list.add(root.data);
		inorder(root.right,list);
	}

	public ArrayList<T> sort(){
		ArrayList<T> list = new ArrayList<T>(size);
		inorder(this.root,list);
		return list;
	}
	
	
}
