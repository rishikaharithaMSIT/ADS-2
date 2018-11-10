import java.util.Scanner;
public class Solution {

	public static void main(String[] args) {
		// Self loops are not allowed...
		// Parallel Edges are allowed...
		// Take the Graph input here...
		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		EdgeWeightedGraph edgeGraph = new EdgeWeightedGraph(vertices);
		int k = edges;
		while (k > 0) {
			String[] edgeString = scan.nextLine().split(" ");
			Edge ed = new Edge(Integer.parseInt(edgeString[0]),
			                   Integer.parseInt(edgeString[1]), Integer.parseInt(edgeString[2]));
			edgeGraph.addEdge(ed);
			k--;
		}

		String caseToGo = scan.nextLine();
		switch (caseToGo) {
		case "Graph":
			//Print the Graph Object.
			System.out.println(edgeGraph);
			break;

		case "DirectedPaths":
			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			String[] dirPaths = scan.nextLine().split(" ");
			int s = Integer.parseInt(dirPaths[0]);
			int d = Integer.parseInt(dirPaths[1]);
			DijkstraUndirectedSP sp = new DijkstraUndirectedSP(edgeGraph, s);
			if (sp.hasPathTo(d)) {
				System.out.println(sp.distTo(d));
			} else {
				System.out.println("No Path Found.");
			}
			break;

		case "ViaPaths":
			// Handle the case of ViaPaths, where three integers are given.
			// First is the source and second is the via is the one where path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			String[] viaPaths = scan.nextLine().split(" ");
			s = Integer.parseInt(viaPaths[0]);
			int via = Integer.parseInt(viaPaths[1]);
			d = Integer.parseInt(viaPaths[viaPaths.length - 1]);
			DijkstraUndirectedSP dsp = new DijkstraUndirectedSP(edgeGraph, s);
			if (dsp.hasPathTo(d)) {
				Queue<Integer> que = new Queue<Integer>();
				for (Edge e : dsp.pathTo(via)) {
                    String[] line = e.toString().split(" ");
                    String[] vw = line[0].split("-");
                    que.enqueue(Integer.parseInt(vw[0]));
                    que.enqueue(Integer.parseInt(vw[1]));
                    for(Integer j : que){
                    	System.out.println(j);
                    }
                    que.dequeue();

                }
                DijkstraUndirectedSP two = new DijkstraUndirectedSP(edgeGraph, via);
                for (Edge e : two.pathTo(d)) {
                    String[] line = e.toString().split(" ");
                    String[] vw = line[0].split("-");
                    //System.out.println(e.other(Integer.parseInt(vw[1])));
                }
				System.out.println(dsp.distTo(via) + two.distTo(d));
			} else {
				System.out.println("No Path Found.");
			}
			break;

		default:
			break;
		}

	}
}