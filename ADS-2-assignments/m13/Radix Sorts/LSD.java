/**
 * Class for lsd.
 */
final class LSD {
    /**
     * BITS_PER_BYTE.
     */
    private static final int BITS_PER_BYTE = 8;

    /**
     * Constructs the object.
     */
    private LSD() { }

    /**
     * sort.
     *
     * @param      a     { parameter_description }
     * @param      w     { parameter_description }
     */
    public static void sort(final String[] a, final int w) {
        int n = a.length;
        final int rR = 256;   // extend ASCII alphabet size
        String[] aux = new String[n];

        for (int d = w - 1; d >= 0; d--) {

            // compute frequency counts
            int[] count = new int[rR + 1];
            for (int i = 0; i < n; i++) {
                count[a[i].charAt(d) + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < rR; r++) {
                count[r + 1] += count[r];
            }

            // move data
            for (int i = 0; i < n; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            // copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }

    /**
     * sort.
     *
     * @param      a     { parameter_description }
     */
    public static void sort(final int[] a) {
        final int bits = 32;
        final int rR = 1 << BITS_PER_BYTE;
        final int mask = rR - 1;
        final int w = bits / BITS_PER_BYTE;

        int n = a.length;
        int[] aux = new int[n];

        for (int d = 0; d < w; d++) {

            // compute frequency counts
            int[] count = new int[rR + 1];
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> BITS_PER_BYTE * d) & mask;
                count[c + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < rR ; r++) {
                count[r + 1] += count[r];
            }
            if (d == w - 1) {
                int shift1 = count[rR] - count[rR / 2];
                int shift2 = count[rR / 2];
                for (int r = 0; r < rR / 2; r++) {
                    count[r] += shift1;
                }
                for (int r = rR / 2; r < rR; r++) {
                    count[r] -= shift2;
                }
            }

            // move data
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> BITS_PER_BYTE * d) & mask;
                aux[count[c]++] = a[i];
            }

            // copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }
}
