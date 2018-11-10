import java.util.Scanner;
import java.util.Arrays;
class Stack {
    String[] s;
    int size;
    Stack() {
        s = new String[50];
        size = 0;
    }
    public void push(String ele) {
        try {
            s[size] = ele;
            size++;
            //System.out.println(Arrays.toString(s));
        } catch (Exception e) {
            resize();
            s[size] = ele;
            size++;

        }

    }
    boolean isEmpty() {
        return size == 0;
    }
    public void resize() {
        s = Arrays.copyOf(s , size * 2);
    }
    public String pop() {
        String item =  s[size - 1];
        s[size - 1] = null;
        size--;
        return item;
    }
    public String toString() {
        String p = "";
        for (int i = 0; i < size; i++) {
            p += s[i] + " ";
        }
        return p;

    }

}