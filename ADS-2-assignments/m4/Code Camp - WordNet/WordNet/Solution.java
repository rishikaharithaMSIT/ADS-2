public class Solution {
	public static void main(String[] args) {
		//Scanner scan = new Scanner(System.in);
		String fileName1 = "Files\\" + StdIn.readLine();
		String fileName2 = "Files\\" + StdIn.readLine();
		In file1 = new In(fileName1);
		In file2 = new In(fileName2);
		while(file1.hasNextLine()) {
			System.out.println(file1.readLine());
		}
		while(file2.hasNextLine()) {
			System.out.println(file2.readLine());
		}



	}
}