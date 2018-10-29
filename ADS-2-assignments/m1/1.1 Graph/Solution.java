import java.util.Arrays;
import java.util.Scanner;
/**
 * Interface for graph.
 */
interface Graph {
	public int V();
	public int E();
	public void addEdge(int v, int w);
	public Iterable<Integer> adj(int v);
	public boolean hasEdge(int v, int w);
}
/**
 * Class for matrix.
 */
class Matrix implements Graph {
	/**
	 * vertices.
	 */
	private int vertices;
	private int edges;
	private int[][] matrix;
	Matrix(int v, int e) {
		this.vertices = v;
		this.edges = 0;
		matrix = new int[v][v];
	}
	public int V() {
		return vertices;
	}
	public int E() {
		return edges;
	}
	public int[][] getMatrix() {
		return matrix;
	}
	public void addEdge(int v, int w) {
		if (v != w && !hasEdge(v, w)) {
			matrix[v][w] = 1;
			matrix[w][v] = 1;
			edges++;
		}


	}
	public Iterable<Integer> adj(int v) {
		return null;
	}
	public boolean hasEdge(int v, int w) {
		return matrix[v][w] == 1;
	}
}
class List implements Graph {
	int vertices;
	int edges;
	SeparateChainingHashST<Integer, Bag> table;
	List(int v, int e) {
		this.vertices = v;
		this.edges = 0;
		table = new SeparateChainingHashST<>();
	}
	public int V() {
		return vertices;
	}
	public int E() {
		return edges;
	}
	public void addEdge(int v, int w) {
		edges++;
		if (v == w || hasEdge(v, w)) edges--;
		if (table.contains(v)) {
			table.get(v).add(w);
			//System.out.println(table.get(v) + " contains " + table.get(v).size());
		} else {
			table.put(v, new Bag<Integer>());
			table.get(v).add(w);
			//System.out.println(table.get(v));
		}
		if (table.contains(w)) {
			table.get(w).add(v);
			//System.out.println(table.get(v) + " contains " + table.get(v).size());
		} else {
			table.put(w, new Bag<Integer>());
			table.get(w).add(v);
			// System.out.println(table.get(v));
		}

	}


	public Iterable<Integer> adj(int v) {
		return table.keys();
	}
	public boolean hasEdge(int v, int w) {
		if (table.contains(v) == false) return false;
		Bag<Integer> b = table.get(v);
		for (Integer each : b) {
			if (each == w) return true;
		}
		return false;
	}
}
public class Solution {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String adjacency = scan.nextLine();
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		switch (adjacency) {
		case "List" :
			List list = new List(vertices, edges);
			String[] elems = scan.nextLine().split(",");
			while (scan.hasNext()) {
				String[] edg = scan.nextLine().split(" ");
				list.addEdge(Integer.parseInt(edg[0]), Integer.parseInt(edg[1]));
			}
			System.out.println(list.vertices + " vertices, " + list.edges + " edges");
			if (list.vertices <= 1 || list.edges <= 1) {
				System.out.println("No edges");
				return;
			}
			// for (Integer eachone : list.table) {
			// 	for (Integer each : eachone) {
			// 		System.out.print(elems[each] + " ");
			// 	}
			// 	System.out.println();
			// }
			for (int ele = 0; ele < vertices; ele++) {
				System.out.print(elems[ele] + ": ");
				Bag<Integer> b = list.table.get(ele);
				for (Integer each : b) {
					//if (each == w) return true;
					System.out.print(elems[each] + " ");
				}
				System.out.println();
			}

			break;
		case "Matrix" :
			Matrix matrix = new Matrix(vertices, edges);
			String[] elements = scan.nextLine().split(",");
			while (scan.hasNext()) {
				String[] edge = scan.nextLine().split(" ");
				matrix.addEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
			}
			System.out.println(matrix.V() + " vertices, " + matrix.E() + " edges");
			if (matrix.V() <= 1 || matrix.E() <= 1) {
				System.out.println("No edges");
				return;
			}
			for (int i = 0; i < vertices; i++) {
				for (int j = 0; j < vertices; j++) {
					//int elem = matrix.matrix[i][j] ? 1 : 0;
					System.out.print(matrix.getMatrix()[i][j]  + " ");
				}
				System.out.println();
			}
			break;
		default:
		}





	}

}
