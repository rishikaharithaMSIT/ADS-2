import java.util.*;
public class BoggleSolver {
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	TreeSet<String> tset;
	boolean[][] marked;
	int index = 0;
	public BoggleSolver(String[] diction) {
		this.tset = new TreeSet<>();
		for (String each : diction) {
			tset.add(each);
		}

	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable.
	public Iterable<String> getAllValidWords(BoggleBoard board) {

		Bag<String> bag = new Bag<String>();
		for (String each : tset) {
			boolean possible = isValid(each, board);
			if (possible) {
				bag.add(each);
			}
		}
		return bag;
	}

	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A through Z.)
	public int scoreOf(String word) {
		return 0;
	}
	boolean isValid(String word, BoggleBoard board) {
		//String[] letters = word.split("");
		for (int i = 0; i < board.rows(); i++) {
			for (int  j = 0; j < board.cols(); j++) {
				if (word.charAt(0) == board.getLetter(i, j)) {
					marked = new boolean[board.rows()][board.cols()];
					boolean yes = isValid(i, j, marked, board, word);
					if (yes) return true;
				}
			}
		}
		return false;
	}
	boolean isValid(int i, int j, boolean[][] marked, BoggleBoard board, String word) {
		index++;
		if (index == word.length()) return true;
		marked[i][j] = true;
		int a = i;
		int b = j;
		if (a >= 0 && b >= 0 && a < board.rows() && b < board.cols()) {
			if (board.getLetter(a, b) == word.charAt(index) && !marked[a][b]) {
				isValid(a, b, marked, board, word);
			}
		}
		a = i - 1;
		b = j - 1;
		if (a >= 0 && b >= 0 && a < board.rows() && b < board.cols()) {
			if (board.getLetter(a, b) == word.charAt(index) && !marked[a][b]) {
				isValid(a, b, marked, board, word);
			}
		}
		a = i - 1;
		b = j;
		if (a >= 0 && b >= 0 && a < board.rows() && b < board.cols()) {
			if (board.getLetter(a, b) == word.charAt(index) && !marked[a][b]) {
				isValid(a, b, marked, board, word);
			}
		}
		a = i + 1;
		b = j + 1;
		if (a >= 0 && b >= 0 && a < board.rows() && b < board.cols()) {
			if (board.getLetter(a, b) == word.charAt(index) && !marked[a][b]) {
				isValid(a, b, marked, board, word);
			}
		}
		a = i;
		b = j - 1;
		if (a > 0 && b > 0 && a < board.rows() && b < board.cols()) {
			if (board.getLetter(a, b) == word.charAt(index) && !marked[a][b]) {
				isValid(a, b, marked, board, word);
			}
		}
		a = i;
		b = j + 1;
		if (a >= 0 && b >= 0 && a < board.rows() && b < board.cols()) {
			if (board.getLetter(a, b) == word.charAt(index) && !marked[a][b]) {
				isValid(a, b, marked, board, word);
			}
		}
		a = i + 1;
		b = j - 1;
		if (a >= 0 && b >= 0 && a < board.rows() && b < board.cols()) {
			if (board.getLetter(a, b) == word.charAt(index) && !marked[a][b]) {
				isValid(a, b, marked, board, word);
			}
		}
		a = i + 1;
		b = j;
		if (a >= 0 && b >= 0 && a < board.rows() && b < board.cols()) {
			if (board.getLetter(a, b) == word.charAt(index) && !marked[a][b]) {
				isValid(a, b, marked, board, word);
			}
		}
		a = i + 1;
		b = j + 1;
		if (a >= 0 && b >= 0 && a < board.rows() && b < board.cols()) {
			if (board.getLetter(a, b) == word.charAt(index) && !marked[a][b]) {
				isValid(a, b, marked, board, word);
			}
		}
		return false;
	}


}