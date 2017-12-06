package graph;
import linear.Queue;

public class Graph {
	Vertex[] vertices;
	
	public boolean hasPath(int v, int w) {
		boolean[] visited = new boolean[vertices.length];
		for(int i=0;i<visited.length;i++){
			visited[i]=false;
		}
		return pathdfs(v,w,visited);
	}
	private boolean pathdfs(int v,int w, boolean[] visited){
		if(vertices[v]==vertices[w]){
			return true;
		}
		visited[v]=true;
		for(Neighbor ptr = vertices[v].neighbors;ptr!=null;ptr=ptr.next){
			if(!visited[ptr.vertex]){
				if(hasPath(ptr.vertex,w)){
					return true;
				}
			}
		}
		return false;
	}
    // returns an array of indegrees of the vertices, i.e. return[i] is the
    // number of edges that are directed IN TO vertex i
    public int[] indegrees() {
    	int[] indegrees = new int[vertices.length];
    	for(int i=0;i<vertices.length;i++){
    		for(Neighbor ptr=vertices[i].neighbors;ptr!=null;ptr=ptr.next){
    			indegrees[ptr.vertex]++;
    		}
    	}
    	return indegrees;
    }
    public String[] topSortbfs(){//using bfs
    	Queue<Integer> queue = new Queue();
    	int[] indegrees = indegrees();
		int topnum=0;
		String[] tops = new String[vertices.length];
		
    	for(int i=0;i<indegrees.length;i++){
    		if(indegrees[i]==0){
    			tops[topnum++]=vertices[i].name;
    			queue.enqueue(i);
    		}
    	}
    	while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (Neighbor ptr=vertices[v].neighbors; ptr != null; ptr=ptr.next) {
                indegrees[ptr.vertex]--;
                if (indegrees[ptr.vertex] == 0) {
                   tops[topnum++] = vertices[ptr.vertex].name;
                   queue.enqueue(ptr.vertex);
                }    
            }
        }
        return tops;
    }
    /*public String[] topSortdfs(){//using dfs
    	int topnum=vertices.length;
    	String[] tops = new String[vertices.length];
    	int[] indegrees = indegrees();
    	boolean[] visited = new boolean[vertices.length];
    	for(int i=0;i<indegrees.length;i++){
    		if(indegrees[i]==0){
    	    	topdfs(i,visited,tops,topnum);
    		}
    	}
    	return tops;
    }
    private void topdfs(int v,boolean[] visited,String[] tops,int topnum){
    	visited[v]=true;
    	for(Neighbor ptr=vertices[v].neighbors;ptr!=null;ptr=ptr.next){
    		if(!visited[ptr.vertex]){
    			topdfs(ptr.vertex,visited,tops,topnum);
    		}
    		
    	}
    }*/
    private void dfs(int v,boolean[] visited){
    	visited[v]=true;
    	for(Neighbor ptr=vertices[v].neighbors;ptr!=null;ptr=ptr.next){
    		if(!visited[ptr.vertex]){
    			dfs(ptr.vertex,visited);
    		}
    	}
    }
    private void bfs(int v,boolean[] visited){
    	Queue queue = new Queue();
    	visited[v]=true;
    	queue.enqueue(vertices[v]);
    	while(!queue.isEmpty()){
    		Vertex w = (Vertex) queue.dequeue();
    		for(Neighbor ptr=w.neighbors;ptr!=null;ptr=ptr.next){
    			if(visited[ptr.vertex]==false){
    				visited[ptr.vertex]=true;
    		    	queue.enqueue(vertices[ptr.vertex]);
    			}
    		}
    	}
    }
}
class Neighbor {
	public int vertex;
	public Neighbor next;

}

class Vertex {
	String name;
	Neighbor neighbors; // adjacency linked lists for all vertices
}
