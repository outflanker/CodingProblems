package CodingProblems;

/**
 * 
 * @author Siddharth
 * Given an adjacency matrix, find if there exist a 'sink' in O(n) time
 */
public class FindSink {
	public static void main(String args[]){
		int n=5;
		int adjacency_matrix[][]=new int[][]{
			  { 0, 1, 1, 0, 0 },
			  { 0, 0, 1, 0, 1 },
			  { 0, 0, 0, 0, 0 },
			  { 1, 0, 1, 0, 0 },
			  { 0, 0, 1, 1, 0 }
			};
	    int candidate = 0;
	    for(int i = 1; i < n; i++){
	        if(adjacency_matrix[candidate][i] == 1){
	        	candidate = i;
	        }
	    }
	    for(int i = 0; i < n; i++){
	        if((i != candidate) && ((adjacency_matrix[i] [candidate] == 0) || (adjacency_matrix[candidate][i] == 1))){
	        	 System.out.println("NO SINK");
	        }
	    }
	    System.out.println("Sink= "+candidate);
	}
}
