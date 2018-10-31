/**
 * stack class.
 *
 * @param      <Item>  The item
 */
public class Stack<Item> {
    /**
     * // size of the stack.
     */
    private int size;
    /**
     * // top of stack.
     */
    private Node first;

    /**
     * Class for node.
     */
    private class Node {
        /**
         * item.
         */
        private Item item;
        /**
         * next node.
         */
        private Node next;
    }

    /**
      * Create an empty stack.
      */
    public Stack() {
        first = null;
        size = 0;
    }



    /**
     * Is the stack empty?.
     * Time complexity for this method is O(1).
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Return the number of items in the stack.
     * Time complexity for this method is O(1).
     *
     * @return     { size }
     */
    public int size() {
        return size;
    }

    /**
     * add an item to stack.
     * Time complexity for this method is O(1).
     *
     * @param      item  The item.
     */
    public void push(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        size++;
    }
}

