import java.util.Scanner;
/**
 * Class for directed cycle.
 */
class DirectedCycle {
    /**
     * { marked array }.
     */
    private boolean[] marked;
    /**
     * { edgeTo array }.
     */
    private int[] edgeTo;
    /**
     * { onStack array }.
     */
    private boolean[] onStack;
    /**
     * { cycle stack }.
     */
    private Stack<Integer> cycle;
    /**
     * Constructs the object.
     *
     * @param      testgraph  The testgraph
     */
    DirectedCycle(final Digraph testgraph) {
        marked  = new boolean[testgraph.vertex()];
        onStack = new boolean[testgraph.vertex()];
        edgeTo  = new int[testgraph.vertex()];
        for (int v = 0; v < testgraph.vertex(); v++) {
            if (!marked[v] && cycle == null) {
                dfs(testgraph, v);
            }
        }
    }
    /**
     * { dfs function }.
     *
     * @param      testgraph  The testgraph
     * @param      v          { parameter_description }
     */
    private void dfs(final Digraph testgraph, final int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : testgraph.adj(v)) {
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(testgraph, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }
    /**
     * Determines if it has cycle.
     *
     * @return     True if has cycle, False otherwise.
     */
    public boolean hasCycle() {
        return cycle != null;
    }
}
/**
 * { Solution class }.
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
        Digraph graphobj = new Digraph(vertices);
        for (int i = 0; i < edges; i++) {
            String[] edgevals = scan.nextLine().split(" ");
            graphobj.addEdge(
                Integer.parseInt(
                    edgevals[0]), Integer.parseInt(
                    edgevals[1]));
        }
        DirectedCycle directedcycleobj = new DirectedCycle(graphobj);
        if (directedcycleobj.hasCycle()) {
            System.out.println("Cycle exists.");
        } 
    }
}
