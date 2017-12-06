package search;

public class Hashtable {
	Node[] entries;
    int numvalues;
    int tableSize;
    float max_load_factor;
    public Hashtable(float max_load_factor,int tableSize) {
    	this.max_load_factor = max_load_factor;
    	entries = new Node[tableSize];
    }
    
    public static void main(String[] args){
    	Hashtable table = new Hashtable(1.0f,3);
    	table.insert(1,"Hi");
    	table.insert(2,"I'm");
    	table.insert(3,"Caleb");
    	table.insert(4,"Emily");
    	table.printTable();
    }
    
    public void insert(int key, String value) {
    	int hashCode = hashFunction(key);
    	if(entries[hashCode]==null){
    		entries[hashCode]=new Node(key,value);
    	}else{
    		Node n = new Node(key,value);
    		n.next=entries[hashCode];
    		entries[hashCode]=n;
    	}
    	numvalues++;
    	if((float)numvalues/entries.length>=max_load_factor){
    		rehash();
    	}
    }
    private void rehash() {
    	Node[] temp = entries;
    	entries=new Node[2*entries.length];
    	for(int i=0;i<temp.length;i++){
    		for(Node n=temp[i];n!=null;n=n.next){
    			//insert(n.key,n.value);
    			int hashCode = hashFunction(n.key);
    			if(entries[hashCode]==null){
    				n.next=null;
    				entries[hashCode]=n;
    			}else{
    				n.next=entries[hashCode];
    				entries[hashCode]=n;
    			}
    			numvalues++;
    		}
    	}
		if((float)numvalues/entries.length>=max_load_factor){
    		rehash();
    	}
    }
    private int hashFunction(int key){
    	int code = key%entries.length;
    	if(code>entries.length-1){
    		code=code%entries.length;
    	}
    	return code;
    }
    private void printTable(){
    	for(int i=0;i<entries.length;i++){
    		for(Node n=entries[i];n!=null;n=n.next){
    			System.out.print(n.toString());
    		}
    		System.out.println();
    	}
    }
}
class Node {
    int key;
    String value;
    Node next;
    public Node(int key,String value){
    	this.key=key;
    	this.value=value;
    }
    public String toString(){
    	return key+" & "+value+" ";
    }
}
