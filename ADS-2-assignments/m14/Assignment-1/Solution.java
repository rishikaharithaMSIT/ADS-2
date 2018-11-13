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
        String[] words = loadWords();
        //Your code goes here...
        Tst<Integer> tstobj = new Tst<>();
        for (int i = 0; i < words.length; i++) {
            String eachword = words[i];
            for (int j = 0; j < eachword.length(); j++) {
                String subword
                    = eachword.substring(
                          j, eachword.length());
                tstobj.put(subword, i);
            }
        }
        In testin = new In();
        String prefix = testin.readLine();
        for (String eachkey : tstobj.keysWithPrefix(prefix)) {
            System.out.println(eachkey);
        }
    }
    /**
     * Loads words.
     *
     * @return     { String array }
     */
    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}
