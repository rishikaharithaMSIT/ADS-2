/******************************************************************************
 *  Compilation:  javac LookupCSV.java
 *  Execution:    java LookupCSV file.csv keyField valField
 *  Dependencies: ST.java In.java StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/35applications/DJIA.csv
 *                https://algs4.cs.princeton.edu/35applications/UPC.csv
 *                https://algs4.cs.princeton.edu/35applications/amino.csv
 *                https://algs4.cs.princeton.edu/35applications/elements.csv
 *                https://algs4.cs.princeton.edu/35applications/ip.csv
 *                https://algs4.cs.princeton.edu/35applications/morse.csv
 *
 *  Reads in a set of key-value pairs from a two-column CSV file
 *  specified on the command line; then, reads in keys from standard
 *  input and prints out corresponding values.
 *
 *  % java LookupCSV amino.csv 0 3     % java LookupCSV ip.csv 0 1
 *  TTA                                www.google.com
 *  Leucine                            216.239.41.99
 *  ABC
 *  Not found                          % java LookupCSV ip.csv 1 0
 *  TCT                                216.239.41.99
 *  Serine                             www.google.com
 *
 *  % java LookupCSV amino.csv 3 0     % java LookupCSV DJIA.csv 0 1
 *  Glycine                            29-Oct-29
 *  GGG                                252.38
 *                                     20-Oct-87
 *                                     1738.74
 *
 *
 ******************************************************************************/



/**
 *  The {@code LookupCSV} class provides a data-driven client for reading in a
 *  key-value pairs from a file; then, printing the values corresponding to the
 *  keys found on standard input. Both keys and values are strings.
 *  The fields to serve as the key and value are taken as command-line arguments.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/35applications">Section 3.5</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class LookupCSV {

    // Do not instantiate.
    private LookupCSV() { }

    public static void main(String[] args) {

        int keyField = 0;
        int valField = 1;

        // symbol table
        //SequentialSearchST<String, String> st = new SequentialSearchST<String, String>();
        //BinarySearchST<String, String> st = new BinarySearchST<String, String>();
        //BST<String, String> st = new BST<String, String>();
        //RedBlackBST<String, String> st = new RedBlackBST<String, String>();
        //SeparateChainingHashST<String, String> st = new SeparateChainingHashST<String, String>();
        //LinearProbingHashST<String, String> st = new LinearProbingHashST<String, String>();
        // read in the data from csv file

        In input = new In("input3.txt");
        int count = 0;
        while (input.hasNextLine()) {
            String command = input.readLine();
            switch (command) {
            case "insert":
                String file = input.readLine();
                In in = new In(file);
                StopwatchCPU timer1 = new StopwatchCPU();
                Stopwatch timer2 = new Stopwatch();
                long start = System.nanoTime();
                while (in.hasNextLine()) {
                    String line = in.readLine();
                    String[] tokens = line.split(",");
                    String key = tokens[keyField];
                    String val = tokens[valField];
                    st.put(key, val);
                    count++;
                }
                System.out.println(count);
                double time1 = timer1.elapsedTime();
                long time = System.nanoTime() - start;
                double timeInSeconds = time / 1e9;
                //System.out.println(time1 + " insert");
                System.out.println(timeInSeconds + " To Insert");
                break;
            case "queries":
                //System.out.println("query");
                StopwatchCPU t2 = new StopwatchCPU();
                long start1 = System.nanoTime();
                while (input.hasNextLine()) {
                    String s = input.readString();
                    if (st.contains(s)) st.get(s);
                    count--;
                }
                // for (String s : st.keys()) {
                //     st.get(s);
                // }
                double ti2 = t2.elapsedTime();
                //System.out.println(ti2 + " search");
                long time3 = System.nanoTime() - start1;
                double timeInSeconds1 = time3 / 1e9;
                System.out.println(timeInSeconds1 + " To Search");
                return;
                //break;
            default:
                System.out.println("Invalid");
            }

        }



        // double time2 = timer2.elapsedTime();
        // System.out.println(time2 + " non CPU");


    }
}

/******************************************************************************
 *  Copyright 2002-2018, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
