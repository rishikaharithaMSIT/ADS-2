import java.util.Arrays;
import java.util.Scanner;
interface Graph {
	public int V();
	public int E();
	public void addEdge(int v, int w);
	public Iterable<Integer> adj(int v);
	public boolean hasEdge(int v, int w);
}
class Matrix implements Graph {
	int vertices;
	int edges;
	int[][] matrix;
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
		if (v == w || hasEdge(v, w)) return;
		if (table.contains(v)) {
			table.get(v).add(w);
			System.out.println(table.get(v) + " contains " + table.get(v).size());
		} else {
			table.put(v, new Bag<Integer>());
			table.get(v).add(w);
			System.out.println(table.get(v));
		}
		edges++;
	}


	public Iterable<Integer> adj(int v) {
		return table.keys();
	}
	public boolean hasEdge(int v, int w) {
		if(table.contains(v) == false) return false;
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
			for (Integer ele : list.table.keys()) {
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
			System.out.println(matrix.vertices + " vertices, " + matrix.edges + " edges");
			if (matrix.vertices <= 1 || matrix.edges <= 1) {
				System.out.println("No edges");
				return;
			}
			for (int i = 0; i < vertices; i++) {
				for (int j = 0; j < vertices; j++) {
					//int elem = matrix.matrix[i][j] ? 1 : 0;
					System.out.print(matrix.matrix[i][j]  + " ");
				}
				System.out.println();
			}
			break;
		default:
		}





	}

}
class SeparateChainingHashST<Key, Value> {
	private static final int INIT_CAPACITY = 4;

	private int n;                                // number of key-value pairs
	private int m;                                // hash table size
	private SequentialSearchST<Key, Value>[] st;  // array of linked-list symbol tables


	/**
	 * Initializes an empty symbol table.
	 */
	public SeparateChainingHashST() {
		this(INIT_CAPACITY);
	}

	/**
	 * Initializes an empty symbol table with {@code m} chains.
	 * @param m the initial number of chains
	 */
	public SeparateChainingHashST(int m) {
		this.m = m;
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
		for (int i = 0; i < m; i++)
			st[i] = new SequentialSearchST<Key, Value>();
	}

	// resize the hash table to have the given number of chains,
	// rehashing all of the keys
	private void resize(int chains) {
		SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(chains);
		for (int i = 0; i < m; i++) {
			for (Key key : st[i].keys()) {
				temp.put(key, st[i].get(key));
			}
		}
		this.m  = temp.m;
		this.n  = temp.n;
		this.st = temp.st;
	}

	// hash value between 0 and m-1
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 *
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size() {
		return n;
	}

	/**
	 * Returns true if this symbol table is empty.
	 *
	 * @return {@code true} if this symbol table is empty;
	 *         {@code false} otherwise
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns true if this symbol table contains the specified key.
	 *
	 * @param  key the key
	 * @return {@code true} if this symbol table contains {@code key};
	 *         {@code false} otherwise
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public boolean contains(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}

	/**
	 * Returns the value associated with the specified key in this symbol table.
	 *
	 * @param  key the key
	 * @return the value associated with {@code key} in the symbol table;
	 *         {@code null} if no such value
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public Value get(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		int i = hash(key);
		return st[i].get(key);
	}

	/**
	 * Inserts the specified key-value pair into the symbol table, overwriting the old
	 * value with the new value if the symbol table already contains the specified key.
	 * Deletes the specified key (and its associated value) from this symbol table
	 * if the specified value is {@code null}.
	 *
	 * @param  key the key
	 * @param  val the value
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void put(Key key, Value val) {
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");
		if (val == null) {
			delete(key);
			return;
		}

		// double table size if average length of list >= 10
		if (n >= 10 * m) resize(2 * m);

		int i = hash(key);
		if (!st[i].contains(key)) n++;
		st[i].put(key, val);
	}

	/**
	 * Removes the specified key and its associated value from this symbol table
	 * (if the key is in this symbol table).
	 *
	 * @param  key the key
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void delete(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");

		int i = hash(key);
		if (st[i].contains(key)) n--;
		st[i].delete(key);

		// halve table size if average length of list <= 2
		if (m > INIT_CAPACITY && n <= 2 * m) resize(m / 2);
	}

	// return keys in symbol table as an Iterable
	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for (int i = 0; i < m; i++) {
			for (Key key : st[i].keys())
				queue.enqueue(key);
		}
		return queue;
	}



}
