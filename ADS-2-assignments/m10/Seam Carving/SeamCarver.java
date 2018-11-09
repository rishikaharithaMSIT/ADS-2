import java.awt.Color;
public class SeamCarver {
	Picture picture;
	double[][] matrix;
	// create a seam carver object based on the given picture
	public SeamCarver(Picture pic) throws Exception {
		if (pic == null) throw new Exception("picture is null");
		this.picture = pic;
		matrix = new double[picture.width()][picture.height()];
		for (int i = 0; i < picture.width(); i++) {
			for (int j = 0; j < picture.height(); j++) {
				matrix[i][j] = 1000.0;
			}
		}
		setEnergyMatrix(picture.width(), picture.height());

	}
	public void setEnergyMatrix(int w, int h) {
		for (int i = 1; i < picture.width() - 1; i++) {
			for (int j = 1; j < picture.height() - 1; j++) {
				matrix[i][j] = energyCal(i, j);
				//System.out.print(matrix[i][j] + " ");
			}
			//System.out.println();
		}
		// for (int i = 0; i < picture.width(); i++) {
		// 	for (int j = 1; j < picture.height(); j++) {
		// 		//matrix[i][j] = energy(i, j);
		// 		System.out.print(matrix[i][j] + " ");
		// 	}
		// 	System.out.println();
		// }

	}
	// current picture
	public Picture picture() {
		return picture;
	}
	// width of current picture
	public int width() {
		return picture.width();
	}

	// height of current picture
	public int height() {
		return picture.height();
	}
	public double energyCal(int x, int y) {
		Color up = picture.get(x, y - 1);
		Color bottom = picture.get(x, y + 1);
		Color left = picture.get(x - 1, y);
		Color right = picture.get(x + 1, y);
		double upDown = Math.pow((up.getRed() - bottom.getRed()), 2)
		                + Math.pow((up.getGreen() - bottom.getGreen()), 2)
		                + Math.pow((up.getBlue() - bottom.getBlue()), 2);
		double leftRight = Math.pow((left.getRed() - right.getRed()), 2)
		                   + Math.pow((left.getGreen() - right.getGreen()), 2)
		                   + Math.pow((left.getBlue() - right.getBlue()), 2);
		double value = Math.sqrt(upDown + leftRight);

		return value;
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		return matrix[x][y];
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}