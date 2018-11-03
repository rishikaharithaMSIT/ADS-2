import java.util.Scanner;
import java.util.ArrayList;
class PageRank {
	int outDegree;
	int inDegree;
	PageRank(Digraph digraph, Integer vertex) {
		this.outDegree = digraph.outdegree(vertex);
		this.inDegree = digraph.indegree(vertex);
		System.out.println(outDegree + " - " + inDegree);
	}
	// double getPR(int vertex) {

	// }

}

class WebSearch {

}


public class Solution {
	public static void main(String[] args) {
		// read the first line of the input to get the number of vertices

		// iterate count of vertices times 
		// to read the adjacency list from std input
		// and build the graph
		
		
		// Create page rank object and pass the graph object to the constructor
		
		// print the page rank object
		
		// This part is only for the final test case
		
		// File path to the web content
		String file = "WebContent.txt";
		
		// instantiate web search object
		// and pass the page rank object and the file path to the constructor
		
		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky
		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		Digraph digraph = new Digraph(vertices);
		int verticesCopy = vertices;
		while(verticesCopy > 0 ) {
			String[] vertex = scan.nextLine().split(" ");
			int v = Integer.parseInt(vertex[0]);
			for(int w = 1; w < vertex.length; w++) {
				digraph.addEdge(v, Integer.parseInt(vertex[w]));
			}
			verticesCopy--;
		}
		ArrayList<PageRank> prList = new ArrayList<>();
		System.out.println(digraph.V() + " vertices, "+ digraph.E() + " edges ");
		for(int i = 0; i < vertices; i++) {
			System.out.print(i + ": ");
			PageRank pr = new PageRank(digraph, i);
			prList.add(pr);
			for(Integer k : digraph.adj(i)) {
				System.out.print(k + " ");
			}
			System.out.println();
		}
		
	}
}
