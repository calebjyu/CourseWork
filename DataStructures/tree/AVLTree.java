package tree;

import java.util.ArrayList;
import java.util.NoSuchElementException;

class AVLTreeNode<T extends Comparable<T>> {
    public T data;
    public AVLTreeNode<T> left, right;
    public char balanceFactor;   // '-' or '/' or '\'
    public AVLTreeNode<T> parent;
    public int height; 
    public AVLTreeNode(T data,AVLTreeNode<T> parent){
		this.data = data;
		left=null;
		right=null;
		balanceFactor='-';
		parent=null;
		height=0;
	}
    public String toString(){
    	return data+" bf="+balanceFactor+" height="+height;
    }
}

public class AVLTree<T extends Comparable<T>>{
	
	AVLTreeNode<T> root;
	int size;
	
	public AVLTree() {   // constructor initializes tree to empty
		root = null;
		size = 0;
	}
	
    public static void main(String[] args){
    	AVLTree<Integer> avl = new AVLTree<Integer>();
    	ArrayList<Integer> arrL = new ArrayList<Integer>();
    	avl.insert(1);
    	avl.insert(2);
    	avl.insert(3);
    	avl.inorder(avl.root, arrL);
    }
    public T search(T key) {
		AVLTreeNode<T> ptr = root;
		while (ptr != null) {
			int c = key.compareTo(ptr.data);
			if (c == 0) {
				return ptr.data;
			}
			ptr = c < 0 ? ptr.left : ptr.right;
		}
		return null;
	}
	
	public void insert(T item) 
	throws IllegalArgumentException {
		AVLTreeNode<T> prev=null, ptr=root;
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
		AVLTreeNode<T> temp = new AVLTreeNode<T>(item,prev);
		if (prev == null) { // tree was empty
			root = temp;
			size = 1;
			return;
		}
		prev.height++;
		if (c < 0) {
			prev.left = temp;
		} else {
			prev.right = temp;
		}
		size++;
		if(root.height-prev.right.height>=0){
			rotateCase1(root);//incorrect! use ptrs to find unbalanced node
		}
		//if(root.height-prev.left.height>=0){
			
		//}
	}
	
	public T delete(T key) 
	throws NoSuchElementException {
		// search and locate
		AVLTreeNode<T> p=null, x=root;
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
			AVLTreeNode<T> y=x.left;
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
	
	private void inorder(AVLTreeNode<T> root, ArrayList<T> list){
		if(root == null){
			return;
		}

		inorder(root.left,list);
		System.out.println(root.toString());
		list.add(root.data);
		inorder(root.right,list);
	}

	public ArrayList<T> sort(){
		ArrayList<T> list = new ArrayList<T>(size);
		inorder(this.root,list);
		return list;
	}
	public void rotateCase1(AVLTreeNode<T> X) {//x is unbalanced node
		AVLTreeNode<T> R=null;
    	if(X.balanceFactor=='/'){
    		R=X.left;
    		if(R.right!=null){
    			X.left=R.right;
    		}else{
    			X.left=null;
    		}
    		R.right=X;
    		root=R;
    	}else /*if(X.balanceFactor=='\\')*/{
    		R=X.right;
    		if(R.left!=null){
    			X.right=R.left;
    		}else{
    			X.right=null;
    		}
    		R.left=X;
    		root=R;
    	}
    	// change bfs of r and x
        X.balanceFactor = '-';
        R.balanceFactor = '-';
        // x's height goes down by 1, r's is unchanged
        X.height--;
    }
}