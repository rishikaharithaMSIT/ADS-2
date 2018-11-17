import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
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
	 * { function_description }.
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		String cases = scan.nextLine();

		switch (cases) {
		case "loadDictionary":
			// input000.txt and output000.txt
			BinarySearchST<String, Integer> hash
			    = loadDictionary("/Files/t9.csv");
			while (scan.hasNextLine()) {
				String key = scan.nextLine();
				System.out.println(hash.get(key));
			}
			break;

		case "getAllPrefixes":
			// input001.txt and output001.txt
			T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
			while (scan.hasNextLine()) {
				String prefix = scan.nextLine();
				for (String each : t9.getAllWords(prefix)) {
					System.out.println(each);
				}
			}
			break;

		case "potentialWords":
			// input002.txt and output002.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			int count = 0;
			while (scan.hasNextLine()) {
				String t9Signature = scan.nextLine();
				for (String each : t9.potentialWords(t9Signature)) {
					count++;
					System.out.println(each);
				}
			}
			if (count == 0) {
				System.out.println("No valid words found.");
			}
			break;

		case "topK":
			// input003.txt and output003.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			Bag<String> bag = new Bag<String>();
			int k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				bag.add(line);
			}
			for (String each : t9.getSuggestions(bag, k)) {
				System.out.println(each);
			}

			break;

		case "t9Signature":
			// input004.txt and output004.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			bag = new Bag<String>();
			k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				for (String each : t9.t9(line, k)) {
					System.out.println(each);
				}
			}
			break;

		default:
			break;

		}
	}

	/**
	 * { function_description }.
	 *
	 * @param      file  The file
	 *
	 * @return     { description_of_the_return_value }
	 */
	public static String[] toReadFile(final String file) {
		In in = new In(file);
		return in.readAllStrings();
	}
	/**
	 * Loads a dictionary.
	 *
	 * @param      file  The file
	 *
	 * @return     { description_of_the_return_value }
	 */
	public static BinarySearchST<String, Integer> loadDictionary(
	    final String file) {
		BinarySearchST<String, Integer>  st
		    = new BinarySearchST<String, Integer>();
		// your code goes here
		String[] dictionary = toReadFile(file);
		for (int i = 0; i < dictionary.length; i++) {
			String word = dictionary[i].toLowerCase();
			if (st.contains(word )) {
				st.put(word , st.get(word ) + 1);
			} else {
				st.put(word, 1);
			}
		}
		return st;
	}

}
/**
 * Class for t 9.
 */
class T9 {
	/**
	 * { var_description }.
	 */
	TST<Integer> tst;
	/**
	 * Constructs the object.
	 *
	 * @param      st    { parameter_description }
	 */
	public T9(final BinarySearchST<String, Integer> st) {
		// your code goes here
		tst = new TST<>();
		for (String each : st.keys()) {
			tst.put(each, st.get(each));
		}
	}

	/**
	 * Gets all words.
	 *
	 * @param      prefix  The prefix
	 *
	 * @return     All words.
	 */
	public Iterable<String> getAllWords(final String prefix) {
		// your code goes here
		return tst.keysWithPrefix(prefix);
	}
	/**
	 * { function_description }.
	 *
	 * @param      t9Signature  The t 9 signature
	 *
	 * @return     { description_of_the_return_value }
	 */
	public Iterable<String> potentialWords(final String t9Signature) {
		// your code goes here
		// String[] nums = t9Signature.split("");
		ArrayList<String> list = new ArrayList<>();
		for (String each : tst.keys()) {
			String[] word = each.split("");
			String num = "";
			for (String ch : word) {
				if (ch.equals("a")
				        || ch.equals("b") || ch.equals("c")) {
					num = num + "2";
				}
				if (ch.equals("d")
				        || ch.equals("e") || ch.equals("f")) {
					num = num + "3";
				}
				if (ch.equals("g")
				        || ch.equals("h") || ch.equals("i")) {
					num = num + "4";
				}
				if (ch.equals("j")
				        || ch.equals("k") || ch.equals("l")) {
					num = num + "5";
				}
				if (ch.equals("m")
				        || ch.equals("n") || ch.equals("o")) {
					num = num + "6";
				}
				if (ch.equals("p")
				        || ch.equals("q") || ch.equals("r")
				        || ch.equals("s")) {
					num = num + "7";
				}
				if (ch.equals("t")
				        || ch.equals("u") || ch.equals("v")) {
					num = num + "8";
				}
				if (ch.equals("w")
				        || ch.equals("x") || ch.equals("y")
				        || ch.equals("z")) {
					num = num + "9";
				}
			}
			if (num.equals(t9Signature)) {
				if (tst.contains(each)) {
					list.add(each);
				}

			}
		}
		return list;
	}

	/**
	 * Gets the suggestions.
	 *
	 * @param      words  The words
	 * @param      k      { parameter_description }
	 *
	 * @return     The suggestions.
	 */
	public Iterable<String> getSuggestions(
	    final Iterable<String> words, final int k) {
		// your code goes here
		// System.out.print("Possible : ");
		// for(String one : words) {
		// 	System.out.print(one + " " + tst.get(one) + " ");
		// }
		//System.out.println();

		ArrayList<String> al = new ArrayList<>();
		BinarySearchST<Integer, ArrayList<String>> bst
		    = new BinarySearchST<>();
		MaxPQ<Integer> maxpq = new MaxPQ<>();
		for (String each : words) {
			if (tst.contains(each)) {
				maxpq.insert(tst.get(each));
			}

		}
		for (String each : words) {
			int freq = tst.get(each);
			if (bst.contains(freq)) {
				ArrayList<String> arr = bst.get(freq);
				arr.add(each);
				Collections.sort(arr);
				bst.put(freq, arr);
			} else {
				bst.put(freq, new ArrayList<String>());
				ArrayList<String> arr = bst.get(freq);
				arr.add(each);
				Collections.sort(arr);
				bst.put(freq, arr);
			}
		}
		int i = 0;

		while (true) {
			if (maxpq.isEmpty()) break;
			int m = maxpq.delMax();
			ArrayList<String> arr2 = bst.get(m);
			//System.out.println(arr2);
			for (int j = 0; j < arr2.size(); j++) {
				al.add(arr2.get(j));
				i++;
				//System.out.println(i + "-"+ k);
				if (i >= k) break;
			}
			if (i >= k) break;

		}
		Collections.sort(al);
		//System.out.println(al.size() + " - " + k);
		return al;
	}

	/**
	 * { function_description }.
	 *
	 * @param      t9Signature  The t 9 signature
	 * @param      k            { parameter_description }
	 *
	 * @return     { description_of_the_return_value }
	 */
	public Iterable<String> t9(
	    final String t9Signature, final int k) {
		return getSuggestions(potentialWords(t9Signature), k);
	}
}
