import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * { function_description }.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        String[] words = loadWords();
        //Your code goes here...
        Tst tst = new Tst();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                tst.put(words[i].substring(j), i);
            }

        }
        Scanner scan = new Scanner(System.in);
        String prefixWord = scan.nextLine();
        Iterable<String> prefixs
            = tst.keysWithPrefix(prefixWord);
        for (String each :  prefixs) {
            System.out.println(each);
        }

    }
    /**
     * Loads words.
     *
     * @return     { description_of_the_return_value }
     */
    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}
