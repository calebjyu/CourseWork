package tree;

public class BinaryTree {

	public static void main(String[] args){
		
	}
	public static <T> boolean isomorphic(BTNode<T> T1, BTNode<T> T2) {
		if(T1==null&&T2==null){
			return true;
		}
		if(T1==null||T2==null){
			return false;
		}
		if(isomorphic(T1.left,T2.left)&&isomorphic(T1.right,T2.right)){//||(isomorphic(T1.left,T2.right)&&isomorphic(T1.left,T2.right))
			return true;
		}
		return false;
	}
}
class BTNode<T> {
    T data;
    BTNode<T> left, right;
    BTNode(T data, BTNode<T> left, BTNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
