public class Solution {
	public static void main(String[] args) {

		SequentialSearchST<String, Integer> st
		    = new SequentialSearchST<String, Integer>();
		StopwatchCPU timer1 = new StopwatchCPU();

		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			st.put(key, i);
		}
		double time1 = timer1.elapsedTime();
		System.out.println(time1);
        //StdOut.printf("%e (%.2f seconds)\n", sum1, time1);
	// 	for (String s : st.keys())
	// 		StdOut.println(s + " " + st.get(s));
	// }

}