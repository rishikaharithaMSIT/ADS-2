/**
 * Class for test.
 *
 * @param      <Value>  The value
 */
public class Tst<Value> {
    /**
     * // size.
     */
    private int n;
    /**
     * // root of TST.
     */
    private Node<Value> root;
    /**
     * Class for node.
     *
     * @param      <Value>  The value
     */
    private static class Node<Value> {
        /**
         * // character.
         */
        private char c;
        /**
         * // left, middle, and right subtries.
         */
        private Node<Value> left, mid, right;
        /**
         * // value associated with string.
         */
        private Value val;
    }

    /**
     * Initializes an empty string symbol table.
     */
    public Tst() {
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     * its complexity is O(1).
     */
    public int size() {
        return n;
    }

    /**
     * Does this symbol table contain the given key?
     * @param key the key
     * @return {true} if this symbol table contains {key} and
     *     { false} otherwise
     *its complexity is O(1).
     */
    public boolean contains(final String key) {

        return get(key) != null;
    }

    /**
     * Returns the value associated with the given key.
     * @param key the key
     * @return the value associated with the given key if the
     *  key is in the symbol table
     *     and {null} if the key is not in the symbol table
     *its complexity is O(L*log(N)).
     */
    public Value get(final String key) {


        Node<Value> x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return x.val;
    }



    /**
     *    // return subtrie corresponding to given key.
     *
     * @param      x     { node }
     * @param      key   The key
     * @param      d     { value in string }
     * its complexity is O(L*log(N)).
     * @return     { node }
     */
    private Node<Value> get(final Node<Value> x,
     final String key, final int d) {
        if (x == null) {
            return null;
        }

        char c = key.charAt(d);
        if      (c < x.c) {
            return get(x.left,  key, d);
        } else if (c > x.c) {
            return get(x.right, key, d);
        } else if (d < key.length() - 1) {
            return get(x.mid,   key, d + 1);
        } else {
            return x;
        }
    }

    /**
     * Inserts the key-value pair into the symbol table,
     *  overwriting the old value
     * with the new value if the key is already in the symbol table.
     * If the value is { null}, this effectively deletes
     *  the key from the symbol table.
     *  its complexity is O(L*log(N)).
     * @param key the key
     * @param val the value
     *
     */
    public void put(final String key, final Value val) {

        if (!contains(key)) {
            n++;
        }
        root = put(root, key, val, 0);
    }

    /**
     * {Inserts the key-value pair into the symbol table,
     *  overwriting the old value}.
     *  its complexity is O(L*log(N)).
     *
     * @param      x     { node }
     * @param      key   The key
     * @param      val   The value
     * @param      d     { value in string }
     *
     * @return     { node }
     */
    private Node<Value> put(final Node<Value> x, final String key,
                            final Value val, final int d) {
        char c = key.charAt(d);
        Node<Value> m = x;
        if (x == null) {
            m = new Node<Value>();
            m.c = c;
        }
        if  (c < m.c) {
            m.left  = put(m.left,  key, val, d);
        } else if (c > m.c) {
            m.right = put(m.right, key, val, d);
        } else if (d < key.length() - 1)  {
            m.mid   = put(m.mid,   key, val, d + 1);
        } else {
            m.val   = val;
        }
        return m;
    }





    /**
     * Returns all of the keys in the set that start with {prefix}.
     * @param prefix the prefix
     * @return all of the keys in the set that start with { prefix},
     *     as an iterable
     *     its complexity is O(L*log(N)).
     *
     */
    public Iterable<String> keysWithPrefix(final String prefix) {

        Queue<String> queue = new Queue<String>();
        Node<Value> x = get(root, prefix, 0);
        if (x == null) {
            return queue;
        }
        if (x.val != null) {
            queue.enqueue(prefix);
        }
        collect(x.mid, new StringBuilder(prefix), queue);
        return queue;
    }



    /**
     * //all keys in subtrie rooted at x with given prefix.
     * its complexity is O(L*log(N)).
     *
     * @param      x       { node }
     * @param      prefix  The prefix
     * @param      queue   The queue
     */
    private void collect(final Node<Value> x, final StringBuilder prefix,
                         final Queue<String> queue) {
        if (x == null) {
            return;
        }
        collect(x.left,  prefix, queue);
        if (x.val != null) {
            queue.enqueue(prefix.toString() + x.c);
        }
        collect(x.mid,   prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }



}
