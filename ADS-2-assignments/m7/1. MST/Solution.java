import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //unused.
    }
    /**
     * main function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {

        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(vertices);

        while (scan.hasNext()) {
            String[] line = scan.nextLine().split(" ");
            Edge edge = new Edge(Integer.parseInt(line[0]),
                                 Integer.parseInt(line[1]),
                                 Double.parseDouble(line[2]));
            ewg.addEdge(edge);

        }
        KruskalMST mst = new KruskalMST(ewg);
        System.out.format("%.5f", mst.weight());
        //String.format("%.5f", weight);

    }
}
