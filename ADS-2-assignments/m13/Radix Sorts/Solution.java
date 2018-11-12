import java.util.Scanner;
import java.util.Arrays;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * main function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int testcases = Integer.parseInt(scan.nextLine());
        String[] array = new String[testcases];
        for (int i = 0; i < testcases; i++) {
            array[i] = scan.nextLine();
        }

        LSD.sort(array, array[0].length());
        System.out.println(Arrays.toString(array));

    }
}

