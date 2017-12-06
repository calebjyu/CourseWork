package tree;

class User implements Comparable<User>{
	String userID;
	String password;
	String name;
	
	public User(String userID, String password, String name){
		this.userID = userID;
		this.password = password;
		this.name = name;
	}
	public int compareTo(User other){
		return userID.compareTo(other.userID);
	}
}

public class BSTApp {
	
	public static void main(String[] args){
		BST<String> strBST = new BST<String>();
		strBST.insert("Bee Movie");
		strBST.insert("Hello World!");
	}
	
}
