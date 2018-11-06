/**
 * Class for directed edge.
 */
public class DirectedEdge {
	/**
	 * v.
	 */
	private final int v;
	/**
	 * w.
	 */
	private final int w;
	/**
	 * weight.
	 */
	private final double weight;

	/**
	 * Constructs the object.
	 *
	 * @param      v       { parameter_description }
	 * @param      w       { parameter_description }
	 * @param      weight  The weight
	 */
	public DirectedEdge(int v, int w, double weight) {
		if (v < 0) {
			throw new IllegalArgumentException(
			    "Vertex names must be nonnegative integers");
		}
		if (w < 0) {
			throw new IllegalArgumentException(
			    "Vertex names must be nonnegative integers");
		}
		if (Double.isNaN(weight)) {
			throw new IllegalArgumentException(
			    "Weight is NaN");
		}
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	/**
	 * Returns the tail vertex of the directed edge.
	 * @return the tail vertex of the directed edge
	 */
	public int from() {
		return v;
	}

	/**
	 * Returns the head vertex of the directed edge.
	 * @return the head vertex of the directed edge
	 */
	public int to() {
		return w;
	}

	/**
	 * Returns the weight of the directed edge.
	 * @return the weight of the directed edge
	 */
	public double weight() {
		return weight;
	}

	/**
	 * Returns a string representation of the directed edge.
	 * @return a string representation of the directed edge
	 */
	public String toString() {
		return v + "->" + w + " " + String.format("%5.2f", weight);
	}
}