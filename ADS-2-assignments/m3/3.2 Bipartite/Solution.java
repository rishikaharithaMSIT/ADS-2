import java.util.Scanner;
/**
 * Class for bipartite.
 */
class Bipartite {
    /**
     * { isBipartite }.
     */
    private boolean isBipartite;
    /**
     * { color array }.
     */
    private boolean[] color;
    /**
     * { marked array }.
     */
    private boolean[] marked;
    /**
     * { edgeTo array }.
     */
    private int[] edgeTo;
    /**
     * { cycle Stack }.
     */
    private Stack<Integer> cycle;
    /**
     * Constructs the object.
     *
     * @param      g1    The g 1
     */
    Bipartite(final Graph g1) {
        isBipartite = true;
        color  = new boolean[g1.vertex2()];
        marked = new boolean[g1.vertex2()];
        edgeTo = new int[g1.vertex2()];
        for (int v = 0; v < g1.vertex2(); v++) {
            if (!marked[v]) {
                dfs(g1, v);
            }
        }
    }
    /**
     * { dfs function }.
     *
     * @param      g1    The g 1
     * @param      v     { Integer }
     */
    private void dfs(final Graph g1, final int v) {
        marked[v] = true;
        for (int w : g1.adj(v)) {
            if (cycle != null) {
                return;
            }
            if (!marked[w]) {
                edgeTo[w] = v;
                color[w] = !color[v];
                dfs(g1, w);
            }   else if (color[w] == color[v]) {
                isBipartite = false;
                cycle = new Stack<Integer>();
                cycle.push(w);
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
            }
        }
    }
    /**
     * Determines if bipartite.
     *
     * @return     True if bipartite, False otherwise.
     */
    public boolean isBipartite() {
        return isBipartite;
    }
}
/**
 * Class for solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //unused.
    }
    /**
     * { Main function }.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        Graph graphobj = new Graph(vertices);
        for (int i = 0; i < edges; i++) {
            String[] edgevals = scan.nextLine().split(" ");
            graphobj.addEdge(
                Integer.parseInt(
                    edgevals[0]), Integer.parseInt(
                    edgevals[1]));
        }
        Bipartite bipobj = new Bipartite(graphobj);
        if (bipobj.isBipartite()) {
            System.out.println("Graph is bipartite");
        } else {
            System.out.println("Graph is not a bipartite");
        }
    }
}

