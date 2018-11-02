import java.util.*;

public class Solution {
	public static void main(String[] args) {
		//Scanner scan = new Scanner(System.in);
		String fileName1 = "Files\\" + StdIn.readLine();
		String fileName2 = "Files\\" + StdIn.readLine();
		In file1 = new In(fileName1);
		In file2 = new In(fileName2);
		int verticies = 0;
		Hashtable hashtable = new Hashtable();
		while (file1.hasNextLine()) {
			String[] lines = file1.readLine().split(",");
			hashtable.put(Integer.parseInt(lines[0]), lines[1]);
			verticies++;
		}
		Digraph diGraph = new Digraph(verticies);
		while (file2.hasNextLine()) {
			String[] lines = file2.readLine().split(",");
			int v = Integer.parseInt(lines[0]);
			for (int w = 1; w < lines.length; w++) {
				diGraph.addEdge(v, Integer.parseInt(lines[w]));
			}
		}
		String command = StdIn.readLine();
		System.out.println(diGraph.V()+" verticies, "+ diGraph.E()+ " edges");
		for (int i = 0; i < verticies; i++) {
			System.out.print(i + ": ");
			for (Integer k : diGraph.adj(i)) {
				System.out.print(k + " ");
			}
			System.out.println();
		}
	}
}