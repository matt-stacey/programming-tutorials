// VTOL UAV
// Basic Part Object
// Matt Stacey
// 15 Dec 2012

package VTOL;

public class Duct extends Part{

	// data points (for individual surfaces; averaged based off of experimental values)
	private double[] powerDATA      = { 75.0,  80.0,  85.0,  90.0,  95.0, 100.0}; // power in percent
	private double[] apertureDATA   = {  0.0,  10.0,  20.0,  30.0,  40.0,  50.0,  60.0,  70.0,  80.0,  90.0, 100.0}; // aperture in percent
	private double[][] forceDATAW   = {{ 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0}, // W movement moment: nose down
									   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0}, // row 0 = 75%
									   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0}, // row 5 = 100%
									   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0}, // aperture in column
									   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0},
									   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0}};
	private double[][] forceDATAA   = {{ 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0}, // A movement moment: LW down
			   						   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0},
			   						   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0},
			   						   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0},
			   						   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0},
			   						   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0}};
	private double[][] forceDATAS   = {{ 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0}, // S movement moment: nose up
			   						   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0},
			   						   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0},
			   						   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0},
			   						   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0},
			   						   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0}};
	private double[][] forceDATAD   = {{ 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0}, // D movement moment: RW down
									   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0},
									   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0},
									   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0},
									   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0},
									   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0}};
	private double[][] forceDATAQ   = {{ 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0}, // Q movement moment: nose left
									   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0},
									   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0},
									   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0},
									   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0},
									   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0}};
	private double[][] forceDATAE   = {{ 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0}, // E movement moment: nose right
									   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0},
									   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0},
									   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0},
									   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0},
									   { 0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0,   0.0}};
	private double[] force = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}; // W, A, S, D, Q, E based on power/aperture
	
	// variables
	private double aperture; // percentage
	
	// accessors
	public double getAperture() { return this.aperture; }
	public double[] getForce(double[] aperture, double power) {
		// bound aperture/power
/**/		for (int a = 0; a < UAV.FANS; a++) {
			if (aperture[a] > 100.0) { aperture[a] = 100.0; }
			if (aperture[a] < 0.0) { aperture[a] = 0.0; }
		}
		if (power > 100.0) { power = 100.0; }
		if (power < 75.0) { power = 75.0; } // assuming at high power when hovering
		
		// aperture = col
/**/		int[] col1 = new int[UAV.FANS];
/**/		int[] col2 = new int[UAV.FANS];
/**/		for (int a = 0; a < UAV.FANS; a++) {
			col1[a] = (int) Math.floor(aperture[a] / 10.0);
			col2[a] = (int) Math.ceil(aperture[a] / 10.0);
			if (col1[a] == col2[a]) {
				if (col1[a] == 10) { col1[a]--; }
				else { col2[a]++; }
			}
		}
		
		// power = row
		int row1, row2;
		row1 = (int) Math.floor((power - 75.0) / 5.0);
		row2 = (int) Math.ceil((power - 75.0) / 5.0);
		if (row1 == row2) {
			if (row1 == 5) { row1--; }
			else { row2++; }
		}
		
		
		
		return this.force;
	}
	
	// mutators/helpers
	public void setAperture(double aperture) { // this impacts forces and moments; recalculated as soon as a new aperture is set
		this.aperture = aperture;
		this.rotation = Matrix.setRotation(this.theta, this.phi, this.psi);
	}
	
	// constructor
	public Duct() {
		setAperture(0.0);
	}
}