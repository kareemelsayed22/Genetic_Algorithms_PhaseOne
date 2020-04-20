import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner; 
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class HamiltonianCycle {

	int path[];
	
	boolean isSafe(int v, int graph[][], int path[], int pos) {
		if(graph[path[pos - 1]][v] == 0) 
			return false;
		for(int i = 0; i<pos; i++)
			if(path[i] == v)
				return false;
		return true;
	}
	
	boolean hamCycleUtil(int graph[][], int path[], int pos, int V) {
		if(pos == V) {
			if(graph[path[pos - 1]][path[0]] == 1)
				return true;
			else 
				return false;
		}
		for(int v = 1; v < V; v++) {
			if(isSafe(v, graph, path, pos)) {
				path[pos] = v;
				if(hamCycleUtil(graph, path, pos + 1, V) == true)
					return true;
				path[pos] = -1;
			}
		}
		return false;
	}
	
	String hamCycle(int graph[][], int V) {
		path = new int[V];
		for(int i = 0; i < V; i++)
			path[i] = -1;
		path[0] = 0;
		if(hamCycleUtil(graph, path, 1, V) == false) {
			System.out.println("\nSolution does not exist");
			return "Solution does not exist";
		}
		printSolution(path, V);
		return Arrays.toString(path);
	}
	
	void printSolution(int path[], int V) {
		System.out.println("Solution Exists: Following is one Hamiltonian Cycle");
		
		for(int i = 0; i < V; i++)
			System.out.print(" " + path[i] + " ");
		System.out.print(" " + path[0] + "\n");
	}
	
	public static void main(String args[]) throws Exception {
		
//		input size variable from input1.txt file
		
		File file1 = new File("input1.txt");
		Scanner sc1 = new Scanner(file1);
		
		List<Integer> size_array = new ArrayList<Integer>();
		
		while(sc1.hasNextLine()) {
			size_array.add(Integer.parseInt(sc1.nextLine()));
		}	
		
		int V1 = size_array.get(0);
		int V2 = size_array.get(1);
		int V3 = size_array.get(2);
		
		String output1, output2, output3;
		
//		input graph from input2.txt file
		
		Scanner sc2 = new Scanner(new BufferedReader(new FileReader("input2.txt")));
	    int rows1 = V1;
	    int columns1 = V1;
	    int [][] graph1 = new int[rows1][columns1];
		
	    int rows2 = V2;
	    int columns2 = V2;
	    int [][] graph2 = new int[rows2][columns2];
	    
	    int rows3 = V3;
	    int columns3 = V3;
	    int [][] graph3 = new int[rows3][columns3];
	    
	    while(sc2.hasNextLine()) {
	         for (int i=0; i<graph1.length; i++) {
	            String[] line = sc2.nextLine().trim().split(" ");
	            for (int j=0; j<line.length; j++) {
	            	graph1[i][j] = Integer.parseInt(line[j]);
	            }
	         }
	         for (int i=0; i<graph2.length; i++) {
		        String[] line2 = sc2.nextLine().trim().split(" ");
		        for (int j=0; j<line2.length; j++) {
		          	graph2[i][j] = Integer.parseInt(line2[j]);
		        }
		     }
	         for (int i=0; i<graph3.length; i++) {
			    String[] line3 = sc2.nextLine().trim().split(" ");
			    for (int j=0; j<line3.length; j++) {
			    	graph3[i][j] = Integer.parseInt(line3[j]);
			    }
			 }
	    }
	    
		HamiltonianCycle hamiltonian = new HamiltonianCycle();
		
		output1 =  hamiltonian.hamCycle(graph1, V1);
		
		output2 =  hamiltonian.hamCycle(graph2, V2);
		
		output3 =  hamiltonian.hamCycle(graph3, V3);
		
		try {
		      FileWriter myWriter = new FileWriter("output.txt");
		      myWriter.write("graph1 = " + output1);
		      myWriter.write("\n");
		      myWriter.write("graph2 = " + output2);
		      myWriter.write("\n");
		      myWriter.write("graph3 = " + output3);
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
}
