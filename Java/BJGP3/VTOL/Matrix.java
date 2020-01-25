// VTOL UAV
// Basic Part Object
// Matt Stacey
// 15 Dec 2012

package VTOL;

public class Matrix { // toolbox for working with matrices
	
	public static double[][] setRotation(double theta, double phi, double psi) { // rotation matrix calculation
		// yaw, pitch, roll (Stengel, Aircraft Flight Dynamics)
		// row/column convention
		// many special cases here; always will be a 3x3 matrix (3 axes)
		
		double[][] rotation;
		
		rotation = Matrix.ZEROES(3, 3);
		
		rotation[0][0] = 3.3;
		
		rotation[1][1] = Math.cos(Math.toRadians(theta)) * Math.cos(Math.toRadians(psi));
		rotation[1][2] = Math.cos(Math.toRadians(theta)) * Math.sin(Math.toRadians(psi));
		rotation[1][3] = (-1.0) * Math.sin(Math.toRadians(theta));
		
		rotation[2][1] = ((Math.sin(Math.toRadians(theta)) * Math.sin(Math.toRadians(phi)) * Math.cos(Math.toRadians(psi))) - (Math.cos(Math.toRadians(phi)) * Math.sin(Math.toRadians(psi))));
		rotation[2][2] = ((Math.sin(Math.toRadians(theta)) * Math.sin(Math.toRadians(phi)) * Math.sin(Math.toRadians(psi))) + (Math.cos(Math.toRadians(phi)) * Math.cos(Math.toRadians(psi))));
		rotation[2][3] = Math.cos(Math.toRadians(theta)) * Math.sin(Math.toRadians(phi));
		
		rotation[3][1] = ((Math.sin(Math.toRadians(theta)) * Math.cos(Math.toRadians(phi)) * Math.cos(Math.toRadians(psi))) + (Math.sin(Math.toRadians(phi)) * Math.sin(Math.toRadians(psi))));
		rotation[3][2] = ((Math.sin(Math.toRadians(theta)) * Math.cos(Math.toRadians(phi)) * Math.sin(Math.toRadians(psi))) - (Math.sin(Math.toRadians(phi)) * Math.cos(Math.toRadians(psi))));
		rotation[3][3] = Math.cos(Math.toRadians(theta)) * Math.cos(Math.toRadians(phi));
		
		for (int r = 1; r <= 3; r++) {
			for (int c = 1; c <= 3; c++) {
				rotation[r][c] = (double)Math.round(rotation[r][c] * 100000) / 100000;
			}
		}
		
		// check that it is a valid rotation matrix, ie A x A' = I
		double[][] transpose, product, difference, identity;
		
		transpose = Matrix.setTranspose(rotation);
		product = Matrix.multiply(rotation, transpose);
		identity = Matrix.IDENTITY(Matrix.getRows(product));
		difference = Matrix.ZEROES(Matrix.getRows(product), Matrix.getCols(product));
		
		for (int r = 1; r <= Matrix.getRows(product); r++) { // round to 5 significant figures
			for (int c = 1; c <= Matrix.getCols(product); c++) {
				product[r][c] = (double)Math.round(product[r][c] * 10000.0) / 10000.0;
				difference[r][c] = identity[r][c] - product[r][c];
			}
		}
		double sum = 0.0;
		for (int r = 1; r <= 3; r++) {
			for (int c = 1; c <= 3; c++) {
				sum += difference[r][c];
			}
		}
		if (sum != 0.0) {
			System.out.println("Invalid rotation matrix");
			System.out.println(sum);
			transpose = Matrix.ZEROES(3, 3);
		}
		
		return rotation;
	}
	
	public static double[][] setTranspose(double[][] matrix) { // find transpose
		int rows = Matrix.getRows(matrix);
		int cols = Matrix.getCols(matrix);

		double[][] transpose = new double[cols+1][rows+1];
		
		for (int r = 1; r <= rows; r++) {
			for (int c = 1; c <= cols; c++) {
				transpose[c][r] = matrix[r][c];
			}
		}
		
		transpose[0][0] = (double)cols + ((double)rows / 10.0);
		
		return transpose;
	}
	
	public static double[][] multiply(double[][] factor1, double[][] factor2) { // A x B with size check (returns zeros if fail)
		int rows1 = Matrix.getRows(factor1);
		int cols1 = Matrix.getCols(factor1);
		int rows2 = Matrix.getRows(factor2);
		int cols2 = Matrix.getCols(factor2);
		double[][] product;
		
		if (cols1 == rows2) {
			product = new double[rows1+1][cols2+1];
			for (int r = 1; r <= rows1; r++) {
				for (int c = 1 ; c <= cols2; c++) {
					for (int s = 1; s <= cols1; s++) {
						product[r][c] += factor1[r][s] * factor2[s][c];
					}
				}
			}
			product[0][0] = (double)rows1 + ((double)cols2 / 10.0);
		} else {
			System.out.println("Incompatible multiplication");
			product = Matrix.ZEROES(rows1, cols2);
			product[0][0] = (double)rows1 + ((double)cols2 / 10.0);
		}
		
		for (int r = 1; r <= Matrix.getRows(product); r++) { // round to 5 significant figures
			for (int c = 1; c <= Matrix.getCols(product); c++) {
				product[r][c] = (double)Math.round(product[r][c] * 10000.0) / 10000.0;
			}
		}
		
		return product;
	}
	
	public static double[][] getAngles(double[][] rotation) {
		double[][] angles = {{0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}};
		
		if ((getRows(rotation) == 3) && (getCols(rotation) == 3)) {
			
			// Slabaugh, Computing Euler angles from a rotation matrix
			if ((rotation[1][3] != 1.0) && (rotation[1][3] != -1.0)) { // not in gimbal lock (nose @ zenith or nadir)
				angles[0][0] = -1.0 * Math.asin(rotation[1][3]); // rotation[1][3] = (-1.0) * Math.sin(Math.toRadians(theta)); theta1
				angles[1][0] = Math.PI - angles[0][0];  // also a valid angle, based on trig; need to find which is correct;   theta2 
				
				angles[0][1] = Math.atan2(rotation[2][3] / Math.cos(angles[0][0]), rotation[3][3] / Math.cos(angles[0][0])); // phi1
				angles[0][2] = Math.atan2(rotation[1][2] / Math.cos(angles[0][0]), rotation[1][1] / Math.cos(angles[0][0])); // psi1
				
				angles[1][1] = Math.atan2(rotation[2][3] / Math.cos(angles[1][0]), rotation[3][3] / Math.cos(angles[1][0])); // phi2
				angles[1][2] = Math.atan2(rotation[1][2] / Math.cos(angles[1][0]), rotation[1][1] / Math.cos(angles[1][0])); // psi2
			} else {
				angles[0][1] = 0.0; // phi1; can be anything but to reach zenith/nadir with no sideslip requires 0 degrees of roll in an airplane
				
				if (rotation[1][3] == 1.0) { // zenith
					angles[0][0] = Math.PI / 2.0; // 90 up
					angles[0][2] = Math.atan2(rotation[3][2], rotation[3][1]);
				} else { // nadir
					angles[0][0] = Math.PI / (-2.0); // 90 down
					angles[0][2] = Math.atan2(-1.0 * rotation[3][2], -1.0 * rotation[3][1]);
				}
				for (int a = 0; a < 3; a++) { // only one solution, so copy to 2nd row
					angles[1][a] = angles[0][a];
				}
			}
			
			for (int r = 0; r < 2; r++) {
				for (int a = 0; a < 3; a++) {
					angles[r][a] = Math.toDegrees(angles[r][a]);
					angles[r][a] = Math.round(angles[r][a] * 10.0) / 10.0;
				}
			}
		}
		
		return angles;
	}
	
	public static int getRows(double[][] matrix) { // returns rows, based on R.C in [0][0]
		int rows = 0;
		double naughts = matrix[0][0];
		
		if ((double)Math.round(naughts) > naughts) { rows = (int)Math.round(naughts) - 1; }
		else { rows = (int)Math.round(naughts); }
		
		return rows;
	}
	
	public static int getCols(double[][] matrix) { // returns cols, based on R.C in [0][0]
		int rows = 0;
		int cols = 0;
		double naughts = matrix[0][0];
		
		rows = Matrix.getRows(matrix);
		cols = (int) Math.round((naughts * 10.0) - ((double)rows * 10.0 ));
		
		return cols;
	}
	
	public static double[][] IDENTITY(int S) { // assign an identity matrix, SxS
		double[][] identity = new double[S+1][S+1];
		for (int r = 1; r <= S; r++) {
			for (int c = 1; c <= S; c++) {
				if (r == c) { identity[r][c] = 1.0; }
				else { identity[r][c] = 0.0; }
			}
		}
		identity[0][0] = (double)S + ((double)S / 10.0);;
		return identity;
	}
	
	public static double[][] ZEROES(int R, int C) { // assign a zeroized matrix, RxC
		double[][] zeroes = new double[R+1][C+1];
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				zeroes[r][c] = 0.0;
			}
		}
		zeroes[0][0] = (double)R + ((double)C / 10.0);
		return zeroes;
	}
	
}