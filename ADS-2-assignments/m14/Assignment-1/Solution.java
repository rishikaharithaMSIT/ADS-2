import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		String[] words = loadWords();
		//Your code goes here...
		Tst tst = new Tst();
		for(int i =0; i < words.length;i++) {
			tst.put(words[i], i);
		}
		Scanner scan = new Scanner(System.in);
		String prefixWord = scan.nextLine();
		Iterable<String> prefixs = tst.keysWithPrefix(prefixWord);
		for(String each:  prefixs) {
			System.out.println(each);
		}

	}

	public static String[] loadWords() {
		In in = new In("/Files/dictionary-algs4.txt");
		String[] words = in.readAllStrings();
		return words;
	}
}
