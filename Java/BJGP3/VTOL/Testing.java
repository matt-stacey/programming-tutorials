// VTOL UAV
// Basic Part Object
// Matt Stacey
// 15 Dec 2012

package VTOL;

public class Testing {

	public Testing() {}

	public static void main(String[] args) {
		UAV uav = new UAV();
		
		uav.setTheta(10.5);
		uav.setPhi(52.9);
		uav.setPsi(2569.3);
		
		
		double[][] rot, tran, prod;
		rot = Matrix.ZEROES(3, 3);
		tran = Matrix.ZEROES(3, 3);
		prod = Matrix.ZEROES(3, 3);
		
		rot = Matrix.setRotation(uav.getTheta(), uav.getPhi(), uav.getPsi()); // tests rotation and transposition of 3x3
		tran = Matrix.setTranspose(rot);
		prod = Matrix.multiply(rot, tran);
		
		System.out.println("Rotation Matrix");
		for (int r = 1; r <= 3; r ++) {
			for (int c = 1; c <= 3; c++) {
				System.out.print(rot[r][c] + " ");
			}
			System.out.println("");
		}
		System.out.println("Transpose");
		for (int r = 1; r <= 3; r ++) {
			for (int c = 1; c <= 3; c++) {
				System.out.print(tran[r][c] + " ");
			}
			System.out.println("");
		}
		System.out.println("R-T Product");
		for (int r = 1; r <= 3; r ++) {
			for (int c = 1; c <= 3; c++) {
				System.out.print(prod[r][c] + " ");
			}
			System.out.println("");
		}
		
		/*for (int r = 1; r < 10; r++) { // tests getRows and getCols
			for (int c = 1; c < 10; c++) {
				rot[0][0] = (double)r + ((double)c / 10.0);
				System.out.println(rot[0][0]);
				int rows = Matrix.getRows(rot);
				int cols = Matrix.getCols(rot);
				System.out.println(rows + " x " + cols);
			}
		}*/
		
		double[][] start = {{2.3, 0.0, 0.0, 0.0}, {0.0, 1.0, 2.0, 3.0}, {0.0, 4.0, 5.0, 6.0}}; // test transposing non-square matrices, multiplying result
		double[][] finish = Matrix.setTranspose(start);
		System.out.println("Start");
		for (int r = 0; r <= Matrix.getRows(start); r++) {
			for (int c = 0; c <= Matrix.getCols(start); c++) {
				System.out.print(start[r][c] + " ");
			}
			System.out.println("");
		}
		System.out.println("Finish");
		for (int r = 0; r <= Matrix.getRows(finish); r++) {
			for (int c = 0; c <= Matrix.getCols(finish); c++) {
				System.out.print(finish[r][c] + " ");
			}
			System.out.println("");
		}
		double[][] product = Matrix.multiply(start, finish);
		System.out.println("Product 1");
		for (int r = 0; r <= Matrix.getRows(product); r++) {
			for (int c = 0; c <= Matrix.getCols(product); c++) {
				System.out.print(product[r][c] + " ");
			}
			System.out.println("");
		}
		product = Matrix.multiply(finish, start);
		System.out.println("Product 2");
		for (int r = 0; r <= Matrix.getRows(product); r++) {
			for (int c = 0; c <= Matrix.getCols(product); c++) {
				System.out.print(product[r][c] + " ");
			}
			System.out.println("");
		}product = Matrix.multiply(start, start);
		System.out.println("Product 3");
		for (int r = 0; r <= Matrix.getRows(product); r++) {
			for (int c = 0; c <= Matrix.getCols(product); c++) {
				System.out.print(product[r][c] + " ");
			}
			System.out.println("");
		}
		
		double[][] angles;
		angles = Matrix.getAngles(rot);
		System.out.println("Angles");
		System.out.println(uav.getTheta() + " " + uav.getPhi() + " " + uav.getPsi());
		for (int r = 0; r < 2; r++) {
			for (int a = 0; a < 3; a++) {
				System.out.print(angles[r][a] + " ");
			}
			System.out.println("");
		}
		System.out.println("Doneski");
	}
}
