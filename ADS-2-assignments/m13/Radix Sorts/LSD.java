/**
 * Class for lsd.
 */
class LSD {
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
	public static void sort(String[] a, final int w) {
		int n = a.length;
		int R = 256;   // extend ASCII alphabet size
		String[] aux = new String[n];

		for (int d = w - 1; d >= 0; d--) {

			// compute frequency counts
			int[] count = new int[R + 1];
			for (int i = 0; i < n; i++) {
				count[a[i].charAt(d) + 1]++;
			}

			// compute cumulates
			for (int r = 0; r < R; r++) {
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
	public static void sort(int[] a) {
		final int BITS = 32;
		final int R = 1 << BITS_PER_BYTE;
		final int MASK = R - 1;
		final int w = BITS / BITS_PER_BYTE;

		int n = a.length;
		int[] aux = new int[n];

		for (int d = 0; d < w; d++) {

			// compute frequency counts
			int[] count = new int[R + 1];
			for (int i = 0; i < n; i++) {
				int c = (a[i] >> BITS_PER_BYTE * d) & MASK;
				count[c + 1]++;
			}

			// compute cumulates
			for (int r = 0; r < R; r++) {
				count[r + 1] += count[r];
			}

			// for most significant byte, 0x80-0xFF comes before 0x00-0x7F
			if (d == w - 1) {
				int shift1 = count[R] - count[R / 2];
				int shift2 = count[R / 2];
				for (int r = 0; r < R / 2; r++) {
					count[r] += shift1;
				}
				for (int r = R / 2; r < R; r++) {
					count[r] -= shift2;
				}
			}

			// move data
			for (int i = 0; i < n; i++) {
				int c = (a[i] >> BITS_PER_BYTE * d) & MASK;
				aux[count[c]++] = a[i];
			}

			// copy back
			for (int i = 0; i < n; i++) {
				a[i] = aux[i];
			}
		}
	}
}
