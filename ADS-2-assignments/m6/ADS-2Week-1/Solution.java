import java.util.Scanner;
import java.util.*;

class PageRank {
	int outDegree;
	int inDegree;
	Digraph dg;
	HashMap<Integer, ArrayList<Integer>> incomingVertices;
	HashMap<Integer, Double> values;
	PageRank(Digraph digraph, Integer vertex) {
		this.outDegree = digraph.outdegree(vertex);
		this.inDegree = digraph.indegree(vertex);
		this.dg = digraph;
		incomingVertices = new HashMap<Integer, ArrayList<Integer>>();
		for (int i = 0; i < digraph.V(); i++) {
			for (Integer k : digraph.adj(i)) {
				if (incomingVertices.containsKey(k)) {
					ArrayList<Integer> list = incomingVertices.get(k);
					list.add(i);
					//System.out.println("list :" + list);
					incomingVertices.put(k, list);
				} else {
					ArrayList<Integer> list = new ArrayList<Integer>();
					list.add(i);
					incomingVertices.put(k, list);
					//System.out.println("list :" + list);
				}
			}
		}
		// for (Integer l : incomingVertices.keySet()) {
		// 	System.out.println(l +  " - " +incomingVertices.get(l));
		// }
		//System.out.println(outDegree + " - " + inDegree);
	}
	double getPR(int vertex) {
		if (dg.outdegree(vertex) == 0) {
			return 0.0;
		}
		values = new HashMap<Integer, Double>();
		for (int i = 0; i < dg.V(); i++) {
			values.put(i , 1.0 / dg.V());
		}
		//double initial = 1 / dg.V();
		double rank = 0.0;
		for (int i = 0; i < 1000; i++) {
			ArrayList<Integer> vert = incomingVertices.get(vertex);
			for (int j = 0; j < vert.size(); j++) {
				int key = vert.get(j);
				rank = values.get(key) / dg.outdegree(key);
				values.put(key , rank);
			}

		}
		//System.out.println(rank);
		return rank;

	}

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
		while (verticesCopy > 0 ) {
			String[] vertex = scan.nextLine().split(" ");
			int v = Integer.parseInt(vertex[0]);
			for (int w = 1; w < vertex.length; w++) {
				digraph.addEdge(v, Integer.parseInt(vertex[w]));
			}
			verticesCopy--;
		}
		ArrayList<PageRank> prList = new ArrayList<>();
		System.out.println(digraph.V() + " vertices, " + digraph.E() + " edges ");
		for (int i = 0; i < vertices; i++) {
			System.out.print(i + ": ");
			PageRank pr = new PageRank(digraph, i);
			prList.add(pr);
			System.out.println(pr.getPR(i));
			for (Integer k : digraph.adj(i)) {
				System.out.print(k + " ");
			}
			System.out.println();
		}

	}
}
